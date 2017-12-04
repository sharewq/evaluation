package com.chinapopin.evaluate.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinapopin.commons.StringUtils;
import com.chinapopin.evaluate.bean.XtFwpjxxTemp;
import com.chinapopin.evaluate.dao.XtFwpjxxTempDao;
import com.chinapopin.evaluate.service.XtFwpjxxTempService;
import com.chinapopin.framework.core.message.ResponseMessage;
import com.chinapopin.framework.core.utils.ExecutorServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Think on 2017/7/27.
 */
@Repository
@Transactional
public class XtFwpjxxTempServiceImpl implements XtFwpjxxTempService {
    private Logger logger = LoggerFactory.getLogger(XtFwpjxxTempServiceImpl.class);

    @Resource
    XtFwpjxxTempDao xtFwpjxxTempDao;
    @Resource
    HttpConnectionImpl httpConnectionImpl;
    @Resource
    CommonUtilsImpl commonUtilsImpl;

    private static final String FWPJ_COUNT_ACTION = "/service/fwpj/fwpj_get_count";             //服务评价统计接口
    private static final String FWPJ_DATA_ACTION = "/service/fwpj/fwpj_get_page_data";          //服务评价获取数据接口

    @Value("${deptId}")
    private String deptId;
    @Value("${switch_fwpj}")
    private String switch_fwpj;

    @Override
    public ResponseMessage EvaluateProcess(String startDate) {
        //开关(0:off,1:on)
        if (!StringUtils.isNullOrEmpty(switch_fwpj) && "0".equals(switch_fwpj)) {
            logger.info("get service evaluate data,service evaluation switch is not configured ,or not open ..Time = " + new Date());
            return ResponseMessage.error("服务评价开关没有配置，或者开关关闭没有打开！");
        }

        // license 认证文件
        String license = commonUtilsImpl.getLicense();
        if (StringUtils.isNullOrEmpty(license)) {
            return ResponseMessage.error("get service evaluate data,license is null  ,Time = " + new Date());
        }
        if (StringUtils.isNullOrEmpty(startDate)) {
            logger.info("get service evaluate data ，query date is null ,");
            return ResponseMessage.error("查询服务评价日期为空!");
        }
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("license", license);
        jsonParam.put("strDate", startDate);
        jsonParam.put("deptId", deptId);
        Map<String, String> countMap = httpConnectionImpl.evaluateHttpConnection(jsonParam.toString(), FWPJ_COUNT_ACTION);
        logger.info("get service evaluate data ， countMap = " + countMap.toString() + " Time = " + new Date());
        //查询数据
        if (!"200".equals(countMap.get("code"))) {
            logger.info("get service evaluate data ，connection fail.. map = " + countMap.toString() + " Time = " + new Date());
            return ResponseMessage.error("获取服务评价总数连接失败!");
        }

        JSONObject countObject = new JSONObject();
        String countString = countMap.get("data");
        if (!StringUtils.isNullOrEmpty(countString)) {
            countObject = JSON.parseObject(countString);
        } else {
            logger.info("get service evaluate data,not get the data ,map = " + countMap.toString() + "! Time = " + new Date());
            return ResponseMessage.error("没有获取到服务评价总数数据!");
        }

        int totalPages = 0;
        if (StringUtils.isNullOrEmpty(countObject.getString("totalPages"))) {
            logger.info("get service evaluate data,totalPages is null .Time = " + new Date());
            return ResponseMessage.error("获取到服务评价总数为空，今天总数据为 " + totalPages);
        } else {
            totalPages = Integer.parseInt(countObject.getString("totalPages"));
            if (totalPages == 0) {
                logger.info("get service evaluate data,totalPages = " + totalPages + "Time = " + new Date());
                return ResponseMessage.error("获取到常住人口项目变更总数为空");
            }
        }

        //高并发获取数据并插入数据库。
        for (int page = 1; page <= totalPages; page++) {
            int pageSize = page;
           /* ExecutorServiceUtils.getListeningExecutorService().submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        getFwpjDataByPage(license, startDate, pageSize);
                    } catch (Exception e) {
                        logger.info("高并发获取数据，并插入数据库异常。Exception = " + e.toString());
                    }
                }
            });*/

            //休息10s
            ExecutorServiceUtils.scheduledExecutorService().schedule(new Runnable() {
                @Override
                public void run() {
                    try {
                        getFwpjDataByPage(license, startDate, pageSize);
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        logger.info("get service evaluate data, high concurrent access to data and insert database(xt_fwpjxx_temp).Exception = " + e.toString() + " Time = " + new Date());
                    }
                }
            }, 45, TimeUnit.SECONDS);
        }

        return ResponseMessage.ok();
    }


    /**
     * 获取License
     *
     * @return
     */
    /*private String getLicense() {
       *//* String path = ClassUtils.getDefaultClassLoader().getResource("license.xml").getFile();
        File file = new File(path);*//*
        File file = new File("config/license.xml");
        if (!file.exists()) {
            logger.error(" get evaluate license fiel , does not exist. Time = " + new Date());
            return "";
        }
        InputStream in = null;
        int buf_size = (int) file.length();
        ByteArrayOutputStream bos = new ByteArrayOutputStream(buf_size);
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, buf_size);
            }
            bos.flush();
            String lecinse = StringUtils.encodeBase64(bos.toByteArray());
            return lecinse;
        } catch (Exception e) {
            logger.warn("get evaluate license Exception, Exception = " + e.toString());
        }finally {
            //流不适宜关闭太早
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }*/


    /**
     * 判断服务评价数据是否存在
     *
     * @param slywlsh
     * @return
     */
    private boolean isFwpjExist(String slywlsh) {
        if (StringUtils.isNullOrEmpty(slywlsh)) {
            return false;
        }
        Example example = new Example(XtFwpjxxTemp.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("slywlsh", slywlsh);
        int count = xtFwpjxxTempDao.selectCountByExample(example);
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
    private void getFwpjDataByPage(String license, String startDate, int page) throws Exception {
        //日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        SimpleDateFormat sdfStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("license", license);
        jsonParam.put("strDate", startDate);
        jsonParam.put("deptId", deptId);
        jsonParam.put("pageNumber", page);
        Map<String, String> dataMap = httpConnectionImpl.evaluateHttpConnection(jsonParam.toString(), FWPJ_DATA_ACTION);
        //保存数据
        if (!"200".equals(dataMap.get("code"))) {
            logger.warn("get service evaluate data, get data by pageNumber(" + page + ") fail... dataMap = " + dataMap.toString() + " Time = " + new Date());
            return;
        }
        //解析数据 ，获取到的所用数据 dataObject
        JSONObject dataObject = new JSONObject();
        String dataString = dataMap.get("data");
        if (!StringUtils.isNullOrEmpty(dataString)) {
            dataObject = JSON.parseObject(dataString);
        } else {
            logger.info("get service evaluate data ,evaluate data is null , map = " + dataMap.toString() + "Time = " + new Date());
            return;
        }
        //判断code
        String code = dataObject.getString("code");
        if (code == null || !"000".equals(code)) {
            logger.info("get service evaluate data ,exception !map = " + dataMap.toString() + " code = " + code + " Time = " + new Date());
            return;
        }
        //获取评价数据
        JSONArray evaluateArray = dataObject.getJSONArray("data");
        if (evaluateArray == null) {
            logger.info(" get service evaluate data is null,, Time = " + new Date());
            return;
        }
        //插入数据库

        for (int i = 0; i < evaluateArray.size(); i++) {
            JSONObject zipObject = evaluateArray.getJSONObject(i);
            //判断是否存在(false:不存在，true:存在)
            String slywlsh = zipObject.getString("SLYWLSH");
            if (isFwpjExist(slywlsh)) {
                logger.debug("get service evaluate data and insert database ,the data is exist and return  . Time = " + new Date());
                continue;
            }
            XtFwpjxxTemp xtFwpjxxTemp = new XtFwpjxxTemp();
            xtFwpjxxTemp.setId(StringUtils.createUUID());
            xtFwpjxxTemp.setSlywlxbh(zipObject.getString("SLYWLXBH"));
            xtFwpjxxTemp.setSlywlsh(slywlsh);
            xtFwpjxxTemp.setSldwdm(zipObject.getString("SLDWDM"));
            xtFwpjxxTemp.setSldwmc(zipObject.getString("SLDWMC"));
            xtFwpjxxTemp.setSlrxm(zipObject.getString("SLRXM"));
            xtFwpjxxTemp.setYwssjz(zipObject.getString("YWSSJZ"));
            xtFwpjxxTemp.setSqrxm(zipObject.getString("SQRXM"));
            xtFwpjxxTemp.setSqrlxdh(zipObject.getString("SQRLXDH"));

            if (!StringUtils.isNullOrEmpty(zipObject.getString("SLRBH"))) {
                xtFwpjxxTemp.setSlrbh(Integer.valueOf(zipObject.getString("SLRBH")));
            }
            if (!StringUtils.isNullOrEmpty(zipObject.getString("FWPJJG"))) {
                xtFwpjxxTemp.setFwpjjg(Short.valueOf(zipObject.getString("FWPJJG")));
            }


            try {
                if (!StringUtils.isNullOrEmpty(zipObject.getString("FWPJSJ"))) {
                    String date = sdf.format(sdfStr.parse(zipObject.getString("FWPJSJ")));
                    xtFwpjxxTemp.setFwpjsj(date);
                }
            } catch (ParseException e) {
                logger.warn("get service evaluate data and insert database , count = " + evaluateArray.size() + "ParseException =" + e.toString() + " Time = " + new Date());
                return;
            }
            xtFwpjxxTemp.setCjsj(sdf.format(new Date()));
            xtFwpjxxTempDao.insertSelective(xtFwpjxxTemp);

        }

    }

}
