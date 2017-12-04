package com.chinapopin.evaluate.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinapopin.commons.StringUtils;
import com.chinapopin.evaluate.bean.CzrkXmbgTemp;
import com.chinapopin.evaluate.bean.XtFwpjxxTemp;
import com.chinapopin.evaluate.dao.CzrkXmbgTempDao;
import com.chinapopin.evaluate.dao.XtFwpjxxTempDao;
import com.chinapopin.evaluate.service.CzrkXmbgTempService;
import com.chinapopin.framework.core.message.ResponseMessage;
import com.chinapopin.framework.core.utils.ExecutorServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 常住人口项目变更
 * Created by Admin on 2017/8/24.
 */
@Repository
@Transactional
public class CzrkXmbgTempServiceImpl implements CzrkXmbgTempService {

    private Logger logger = LoggerFactory.getLogger(XtFwpjxxTempServiceImpl.class);
    @Resource
    CzrkXmbgTempDao czrkXmbgTempDao;
    @Resource
    HttpConnectionImpl httpConnectionImpl;
    @Resource
    CommonUtilsImpl commonUtilsImpl;

    private static final String CZRK_XMBG_COUNT_ACTION = "/service/xmbg/xmbg_get_count";             //常住人口项目变更统计接口
    private static final String CZRK_XMBG_DATA_ACTION = "/service/xmbg/xmbg_get_page_data";          //常住人口项目变更获取数据接口

    @Value("${deptId}")
    private String deptId;
    @Value("${switch_czrk}")
    private String switch_czrk;

    /**
     * 获取常住人口项目变更数据
     *
     * @param startDate
     * @return
     */
    public ResponseMessage CzrkXmbgProcess(String startDate) {

        //开关(0:off,1:on)
        if (!StringUtils.isNullOrEmpty(switch_czrk) && "0".equals(switch_czrk)) {
            logger.info("get service czrkxmbg data, czrkxmbg switch is not configured ,or not open ..Time = " + new Date());
            return ResponseMessage.error("常住人口项目变更开关没有配置，或者开关关闭没有打开!");
        }

        // license 认证文件
        String license = commonUtilsImpl.getLicense();
        if (StringUtils.isNullOrEmpty(license)) {
            return ResponseMessage.error("get service czrkxmbg data,license is null  ,Time = " + new Date());
        }
        if (StringUtils.isNullOrEmpty(startDate)) {
            logger.info("get service czrkxmbg data ，query date is null ,");
            return ResponseMessage.error("查询常住人口项目变更日期为空!");
        }
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("license", license);
        jsonParam.put("strDate", startDate);
        jsonParam.put("deptId", deptId);
        logger.info("xmbg start Time  = " + new Date().toString());
        Map<String, String> countMap = httpConnectionImpl.evaluateHttpConnection(jsonParam.toString(), CZRK_XMBG_COUNT_ACTION);
        logger.info("get service czrkxmbg data, countMap = " + countMap.toString() + " Time = " + new Date().toString());
        //查询数据
        if (!"200".equals(countMap.get("code"))) {
            logger.info("get service czrkxmbg data ，connection fail.. map = " + countMap.toString() + " Time = " + new Date());
            return ResponseMessage.error("获取常住人口项目变更总数连接失败!");
        }

        JSONObject countObject = new JSONObject();
        String countString = countMap.get("data");
        if (!StringUtils.isNullOrEmpty(countString)) {
            countObject = JSON.parseObject(countString);
        } else {
            logger.info("get service czrkxmbg data,not get the data ,map = " + countMap.toString() + "! Time = " + new Date());
            return ResponseMessage.error("没有获取到常住人口项目变更总数数据!");
        }

        int totalPages = 0;
        if (StringUtils.isNullOrEmpty(countObject.getString("totalPages"))) {
            logger.info("get service czrkxmbg data,totalPages is null. Time = " + new Date());
            return ResponseMessage.error("获取到常住人口项目变更总数为空");
        } else {
            totalPages = Integer.parseInt(countObject.getString("totalPages"));
            if (totalPages == 0) {
                logger.info("get service czrkxmbg data,totalPages = " + totalPages + "  Time = " + new Date());
                return ResponseMessage.error("获取到常住人口项目变更总数为空");
            }
        }

        //高并发获取数据并插入数据库。
        for (int page = 1; page <= totalPages; page++) {
            int pageSize = page;
            //休息50s
            ExecutorServiceUtils.scheduledExecutorService().schedule(new Runnable() {
                @Override
                public void run() {
                    try {
                        getCzrkXmbgDataByPage(license, startDate, pageSize);
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        logger.info("get service czrkxmbg data, high concurrent access to data and insert database(czrk_xmbg_temp). Exception = " + e.toString() + " Time = " + new Date());
                    }
                }
            }, 45, TimeUnit.SECONDS);
        }
        return ResponseMessage.ok();
    }


    /**
     * 判断服务评价数据是否存在
     *
     * @param ywlsh
     * @return
     */
    private boolean isCzrkXmbgExist(String ywlsh) {
        if (StringUtils.isNullOrEmpty(ywlsh)) {
            logger.info("get service czrkxmbg data, query czrk_xmbg_temp data ，incoming ywlsh is null!");
            return false;
        }
        Example example = new Example(CzrkXmbgTemp.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("ywlsh", ywlsh);
        int count = czrkXmbgTempDao.selectCountByExample(example);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取服务评价数据
     *
     * @return
     */
    private void getCzrkXmbgDataByPage(String license, String startDate, int page) throws Exception {
        //日期格式
      /*  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        SimpleDateFormat sdfStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");*/
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("license", license);
        jsonParam.put("strDate", startDate);
        jsonParam.put("deptId", deptId);
        jsonParam.put("pageNumber", page);
        Map<String, String> dataMap = httpConnectionImpl.evaluateHttpConnection(jsonParam.toString(), CZRK_XMBG_DATA_ACTION);

        //保存数据
        if (!"200".equals(dataMap.get("code"))) {
            logger.warn("get service czrkxmbg data,get data by pageNumber(" + page + ") fail... map = " + dataMap.toString() + " Time = " + new Date());
            return;
        }

        //解析数据 ，获取到的所用数据 dataObject
        JSONObject dataObject = new JSONObject();
        String dataString = dataMap.get("data");
        if (!StringUtils.isNullOrEmpty(dataString)) {
            dataObject = JSON.parseObject(dataString);
        } else {
            logger.info("get service czrkxmbg data ,evaluate data is null , map = " + dataMap.toString() + "Time = " + new Date());
            return;
        }

        //判断code
        String code = dataObject.getString("code");
        if (code == null || !"000".equals(code)) {
            logger.info("get service czrkxmbg data ,exception !map = " + dataMap.toString() + " code = " + code + " Time = " + new Date());
            return;
        }

        //获取评价数据
        JSONArray evaluateArray = dataObject.getJSONArray("data");
        if (evaluateArray == null) {
            logger.info(" get service czrkxmbg data is null,, Time = " + new Date());
            return;
        }

        //插入数据库
        for (int i = 0; i < evaluateArray.size(); i++) {
            JSONObject zipObject = evaluateArray.getJSONObject(i);
            //判断是否存在(false:不存在，true:存在)
            String ywlsh = zipObject.getString("YWLSH");
            if (isCzrkXmbgExist(ywlsh)) {
                logger.debug("get service czrkxmbg data and insert database ,data to exist . Time = " + new Date());
                continue;
            }
            CzrkXmbgTemp czrkXmbgTemp = new CzrkXmbgTemp();
            czrkXmbgTemp.setYwlsh(zipObject.getString("YWLSH"));
            czrkXmbgTemp.setSjxbsf(zipObject.getString("SJXBSF"));
            czrkXmbgTemp.setXm(zipObject.getString("XM"));
            czrkXmbgTemp.setGmsfhm(zipObject.getString("GMSFHM"));
            czrkXmbgTemp.setBggzqnr(zipObject.getString("BGGZQNR"));
            czrkXmbgTemp.setBggzhnr(zipObject.getString("BGGZHNR"));
            czrkXmbgTemp.setCzsj(zipObject.getString("CZSJ"));
            czrkXmbgTemp.setBlsj(zipObject.getString("BLSJ"));
            czrkXmbgTemp.setSldwmc(zipObject.getString("SLDWMC"));
            czrkXmbgTemp.setSldwdm(zipObject.getString("SLDWDM"));
            czrkXmbgTemp.setSlrxm(zipObject.getString("SLRXM"));
            czrkXmbgTemp.setSsxqmc(zipObject.getString("SSXQMC"));
            czrkXmbgTempDao.insertSelective(czrkXmbgTemp);

        }

    }

}
