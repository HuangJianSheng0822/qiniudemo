package com.qn.qiniudemoapi.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class IPUtils {

    /**
     * 获取用户真实IP地址
     * @param request HttpServletRequest对象
     * @return 用户IP地址
     */
    public static String getClientIP(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

        // 如果用户通过多级代理，X-Forwarded-For会包含多个IP地址，其中第一个IP为真实IP
        int commaIndex = ipAddress.indexOf(',');
        if (commaIndex != -1) {
            ipAddress = ipAddress.substring(0, commaIndex);
        }
        return ipAddress;
    }
}