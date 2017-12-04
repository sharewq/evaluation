package com.chinapopin.evaluate.job;

import com.chinapopin.evaluate.service.CzrkXmbgTempService;
import com.chinapopin.evaluate.service.XtFwpjxxTempService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Think on 2017/7/28.
 */
@Component
public class EvaluateJob {
    private Logger logger = LoggerFactory.getLogger(EvaluateJob.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    @Resource
    XtFwpjxxTempService xtFwpjxxTempService;
    @Resource
    CzrkXmbgTempService czrkXmbgTempService;


    //---------------------项目变更--------------------------
    /**
     * 常住人口项目变更，1:40(昨天数据)
     */
    @Scheduled(cron = "0 40 1 * * ?")
    public void czrkXmbgJob_early_morn() {
        Date date = addDday(new Date(), -1);
        czrkXmbgJobProcess(date);
    }

    /**
     * 常住人口项目变更，12:40
     */
    @Scheduled(cron = "0 40 12 * * ?")
    public void czrkXmbgJob_noon() {
        czrkXmbgJobProcess(new Date());
    }

    /**
     * 常住人口项目变更，晚上7:40
     */
    @Scheduled(cron = "0 40 19 * * ?")
    public void czrkXmbgJob_night() {
        czrkXmbgJobProcess(new Date());
    }

    /**
     * 常住人口项目变更，晚上12:40：00
     */
    @Scheduled(cron = "0 40 23 * * ?")
    public void czrkXmbgJob_morn() {
        czrkXmbgJobProcess(new Date());
    }

    //---------------------服务评价-------------------------
    /**
     * 常住人口项目变更，1:40(昨天数据)
     */
    @Scheduled(cron = "0 40 1 * * ?")
    public void evaluateJob_early_morn() {
        Date date = addDday(new Date(), -1);
        evaluateJobProcess(date);
    }

    /**
     * 服务评价中午12:45
     */
    @Scheduled(cron = "0 45 12 * * ?")
    public void evaluateJob_noon() {
        evaluateJobProcess(new Date());
    }

    /**
     * 服务评价晚上7:45
     */
    @Scheduled(cron = "0 45 19 * * ?")
    public void evaluateJob_night() {
        evaluateJobProcess(new Date());
    }

    /**
     * 服务评价晚上12:45：00
     */
    @Scheduled(cron = "0 45 23 * * ?")
    public void evaluateJob_morn() {
        evaluateJobProcess(new Date());
    }

    /**
     * 服务评价数据处理
     */
    private void evaluateJobProcess(Date date) {
        if (null == date) {
            logger.info("定时任务获取服务评价数据，获取日期为空");
            return;
        }
        xtFwpjxxTempService.EvaluateProcess(sdf.format(date));
    }

    /**
     * 常住人口项目变更处理
     */
    private void czrkXmbgJobProcess(Date date) {
        if (null == date) {
            logger.info("定时任务获常住人口项目变更数据，获取日期为空！");
            return;
        }
        czrkXmbgTempService.CzrkXmbgProcess(sdf.format(date));
    }

    /**
     * 日期加减天数
     *
     * @param date
     * @param day
     * @return
     */
    public static Date addDday(Date date, int day) {
        if (null == date) {
            return new Date();
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, day);
        return calendar.getTime();
    }

}
