package com.anji.mirror.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RequestLogUtil {

    public static Logger logger = LoggerFactory.getLogger(RequestLogUtil.class);

    /**获取请求参数
     * @param request
     * @return
     */
    public static String getRequestString(HttpServletRequest request) {
        String result = null;
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8");
            // 包装request的输入流
            BufferedReader br = new BufferedReader(inputStreamReader);
            // 缓冲字符
            StringBuffer sb = new StringBuffer("");
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close(); // 关闭缓冲流
            result = sb.toString(); // 转换成字符
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(inputStreamReader!=null){
                    inputStreamReader.close();
                }
            }catch (Exception e){
                logger.error("close inputStreamReader error:{}",e);
            }
        }
        return result;
    }

    /**获取ip地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
