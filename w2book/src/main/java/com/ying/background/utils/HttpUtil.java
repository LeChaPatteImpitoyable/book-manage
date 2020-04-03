package com.ying.background.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by yingsy on 2018/6/3.
 */
@Slf4j
public class HttpUtil {

    public static final String BOOK_TOKEN = "book_token";
    public static final String PATH_SIGN = "/";
    public static final String UTF8 = "UTF-8";
    public static final int MAX_AGE_12H = 12 * 60 * 60;
    private static final String DOMAN = "cookie.base.path";

    /**
     * 描述: 添加cookie
     * 作者: yingsy
     * 日期: 2018/6/3 21:27
     * @param response
     * @param key
     * @param value
     * @return void
     */
    public static void addCookieValue(HttpServletResponse response, String key, String value) {
        addCookieValue(response, key, value, MAX_AGE_12H);
    }

    /**
     * 描述: 添加cookie
     * 作者: yingsy
     * 日期: 2018/6/3 21:27
     * @param response
     * @param key
     * @param value
     * @param maxAge
     * @return void
     */
    public static void addCookieValue(HttpServletResponse response, String key, String value, int maxAge) {
        Cookie cookie = null;
        try {
            cookie = new Cookie(key, URLEncoder.encode(value,"UTF-8"));
        } catch(UnsupportedEncodingException e) {
            log.error("添加cookie异常", e);
        }
        cookie.setPath(PATH_SIGN);
//        cookie.setDomain(PropertiesUtil.getProperties(DOMAN));
        if (maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }

        response.addCookie(cookie);
    }

    /**
     * 描述: 移除cookie
     * 作者: yingsy
     * 日期: 2018/6/3 21:27
     * @param response
 * @param key
     * @return void
     */
    public static void removeCookie(HttpServletResponse response, String key) {
        Cookie cookie = new Cookie(key, null);
        cookie.setPath(PATH_SIGN);
        cookie.setMaxAge(0);
        cookie.setPath(PATH_SIGN);
        response.addCookie(cookie);
    }

    /**
     * 描述: 获取cookie
     * 作者: yingsy
     * 日期: 2018/6/3 21:27
     * @param request
     * @param key
     * @return java.lang.String
     */
    public static String getCookieValue(HttpServletRequest request, String key) {

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (key.equals(cookie.getName())) {
                    try {
                        return URLDecoder.decode(cookie.getValue(),"UTF-8");
                    } catch(UnsupportedEncodingException e) {
                        log.error("获取cookie异常{}", e, key);
                        return null;
                    }
                }
            }
        }
        return null;
    }

    public static String getClientIp(HttpServletRequest request) {
        String ip = null;
        ip = getDefaultIP(ip, request.getHeader("X-Forwarded-RemoteAddr"));
        ip = getDefaultIP(ip, request.getHeader("X-Forwarded-For"));
        ip = getDefaultIP(ip, request.getHeader("Proxy-Client-IP"));
        ip = getDefaultIP(ip, request.getHeader("WL-Proxy-Client-IP"));
        ip = getDefaultIP(ip, request.getHeader("HTTP_CLIENT_IP"));
        ip = getDefaultIP(ip, request.getRemoteAddr());
        ip = getDefaultIP(ip, "127.0.0.1");
        return ip;
    }

    public static String getDefaultIP(String ip, String defaultIp) {
        if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip.trim())) {
            return ip;
        }

        if (!StringUtils.isEmpty(defaultIp) && !"unknown".equalsIgnoreCase(defaultIp.trim())) {
            return defaultIp.trim();
        }

        return null;
    }
}
