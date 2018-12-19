package com.zxc.user.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * {@link Cookie}工具类
 *
 * @author Zhou RunMing
 * @Date 2018-12-19
 */
public class CookieUtil {

    public static void set(HttpServletResponse response,String key, String value, Integer age) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        cookie.setMaxAge(age);
        response.addCookie(cookie);
    }

}
