package com.chinapopin.evaluate.controller;

import com.chinapopin.evaluate.service.XtFwpjxxTempService;
import com.chinapopin.framework.core.message.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Admin on 2017/7/28.
 */
@RestController
@RequestMapping("/api")
public class XtFwpjxxTempController {

    private Logger logger = LoggerFactory.getLogger(XtFwpjxxTempController.class);
    @Resource
    XtFwpjxxTempService dxLogService;

    @ResponseBody
    @RequestMapping(value = "/evaluate", method = RequestMethod.GET)
    public ResponseMessage getEvaluateData(String startDate) {
        try {

            return dxLogService.EvaluateProcess(startDate);
        } catch (Exception e) {
            logger.info("获取服务评价数据错误");
            return ResponseMessage.error("获取服务评价数据错误。");
        }
    }

}
