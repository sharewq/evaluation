package com.chinapopin.evaluate.controller;

import com.chinapopin.evaluate.service.CzrkXmbgTempService;
import com.chinapopin.framework.core.message.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Admin on 2017/8/24.
 */
@RestController
@RequestMapping("/czrk")
public class CzrkXmbgTempController {
    private Logger logger = LoggerFactory.getLogger(CzrkXmbgTempController.class);

    @Resource
    CzrkXmbgTempService czrkXmbgTempService;

    @ResponseBody
    @RequestMapping(value = "/czrk-xmbg", method = RequestMethod.GET)
    public ResponseMessage getEvaluateData(String startDate) {
        try {

            System.out.println("dev");
            return czrkXmbgTempService.CzrkXmbgProcess(startDate);
        } catch (Exception e) {
            logger.info("获取常住人口项目变更数据错误");
            return ResponseMessage.error("获取常住人口项目变更数据错误。");
        }
    }
}
