package com.chinapopin.evaluate.service;

import com.chinapopin.framework.core.message.ResponseMessage;

/**
 * Created by Admin on 2017/8/24.
 */
public interface CzrkXmbgTempService {

    /**
     * 获取常住人口项目变更数据
     *
     * @param startDate
     * @return
     */
    public ResponseMessage CzrkXmbgProcess(String startDate);
}
