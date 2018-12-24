package com.zxc.apigateway.utils;

/**
 * @author Zhou RunMing
 * @Date 2018-12-24
 */
public class StringUtil {
    public static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }

}
