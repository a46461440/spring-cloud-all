package com.zxc.order.utils;

import java.util.Random;

/**
 * 生成key的工具类
 *
 * @author Zhou RunMing
 * @date 2018/12/16
 */
public class KeyUtil {

    private static Random random = new Random();

    /**
     * 生成唯一主键
     * 格式: 时间+随机数
     */
    public static synchronized String genOrdrUniqueKey() {
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + number.toString();
    }

}
