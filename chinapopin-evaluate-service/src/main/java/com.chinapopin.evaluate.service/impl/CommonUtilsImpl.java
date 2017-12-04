package com.chinapopin.evaluate.service.impl;

import com.chinapopin.commons.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Date;

/**
 * g 工具类
 * Created by Admin on 2017/8/24.
 */
@Component
public class CommonUtilsImpl {

    private static Logger logger = LoggerFactory.getLogger(CommonUtilsImpl.class);
    private static final String LICENSE_PATH = "config/license.xml";          //license 认证文件路径
    private static volatile String LICENSE_FILE = "";                          //license 认证文件


    /**
     * 获取License
     *
     * @return
     */
    public static String getLicense() {
        if (!StringUtils.isNullOrEmpty(LICENSE_FILE)) {
            return LICENSE_FILE;
        }

        File file = new File(LICENSE_PATH);
        if (!file.exists()) {
            logger.error(" 通过工具类文件，get license fail... , lecense file not exist. Time = " + new Date());
            return "";
        }
        logger.info("********************* get license ... **********************");
        InputStream in = null;
        int buf_size = (int) file.length();
        ByteArrayOutputStream bos = new ByteArrayOutputStream(buf_size);
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[buf_size];
            int len = 0;
            int index = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, index, buf_size);
                index += len;
            }
            bos.flush();
            LICENSE_FILE = StringUtils.encodeBase64(bos.toByteArray());
            return StringUtils.encodeBase64(bos.toByteArray());
        } catch (Exception e) {
            logger.warn("throuhgh the tool get license ,file ExceptionTime = " + new Date() + " Exception = " + e.toString());
            return "";
        } finally { //流不适宜关闭太早,
            try {
                if (null != bos) {
                    bos.close();
                }
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.info("get license file ,close file flow IOException, Time = " + new Date() + "IOException = " + e.toString());
                return "";
            }
        }
    }

}
