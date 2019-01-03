package com.zxc.common.commons;

import org.redisson.client.RedisClient;

/**
 * {@link RedisClient}配置参数
 *
 * @author Zhou RunMing
 * @date 2019/1/3
 */
public interface RedisConf {

    String REDIS_ADDRESS = "redis://%s:%s";

}
