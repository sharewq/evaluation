package com.chinapopin.evaluate.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liujiaojiao on 2017/3/7.
 */
@Component
public class HttpConnectionImpl {
    private static Logger logger = LoggerFactory.getLogger(HttpConnectionImpl.class);
    @Value("${evaluateHttpUrl}")
    private String httpUrl;

    public Map<String, String> evaluateHttpConnection(String requestString, String action) {

        Map<String, String> map = new HashMap<>();
        HttpURLConnection httpConn = null;
        try {
            // 建立连接
            //String pathUrl = httpUrl + action + param;
            String pathUrl = httpUrl + action;
            //logger.info("access service evaluation interface，evaluateHttpUrl：" + pathUrl + "  time = " + new Date());
            URL url = new URL(pathUrl);
            httpConn = (HttpURLConnection) url.openConnection();

            // 设置连接属性
            httpConn.setDoOutput(true);             // 使用 URL 连接进行输出
            httpConn.setConnectTimeout(5000);       // 连接数据时间
            httpConn.setReadTimeout(25000);         // 读取数据时间
            httpConn.setDoInput(true);              // 使用 URL 连接进行输入
            httpConn.setUseCaches(false);           // 设置忽略缓存
            httpConn.setRequestMethod("POST");      // 设置URL请求方法
            httpConn.setRequestProperty("Charset", "UTF-8");// 设置文件字符集
            // httpConn.setInstanceFollowRedirects(true);

            // 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
            byte[] requestStringBytes = requestString.getBytes("UTF-8");
            httpConn.setRequestProperty("Content-length", "" + requestStringBytes.length);
            httpConn.setRequestProperty("Content-Type", "application/json");

            // 建立输出流，并写入数据
            OutputStream outputStream = httpConn.getOutputStream();
            outputStream.write(requestStringBytes);
            outputStream.flush();
            outputStream.close();
            // 获得响应状态
            int responseCode = httpConn.getResponseCode();
            //logger.info(" access service evaluation interface，Return the result code: " + responseCode + " time = " + new Date());
            StringBuffer responseResult = new StringBuffer();
            if (HttpURLConnection.HTTP_OK == responseCode) {  // 连接成功
                // 当正确响应时处理数据
                String readLine = new String();
                BufferedReader responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
                while ((readLine = responseReader.readLine()) != null) {
                    responseResult.append(readLine);
                }
                responseReader.close();

                map.put("code", String.valueOf(responseCode));
                map.put("message", "数据获取成功");
                map.put("data", responseResult.toString());
            } else {
                map.put("code", String.valueOf(responseCode));
                map.put("message", "信息获取失败!");
                logger.info("access service evaluation interface，fail..." + "time = " + new Date());
            }

        } catch (Exception ex) {
            map.put("code", "99");
            map.put("message", ex.getMessage());
            logger.error("access service evaluation interface，Exception..." + ex.toString() + " time = " + new Date());
        } finally {
            if (httpConn != null) {
                httpConn.disconnect();
                httpConn = null;
            }
        }
        return map;
    }

}
