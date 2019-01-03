package com.zxc.common.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import static com.zxc.common.commons.RedisConf.REDIS_ADDRESS;

/**
 * TODO...
 *
 * @author Zhou RunMing
 * @date 2019/1/3
 */
@ConfigurationProperties("spring.redis")
public class RedissonConfig {

    private String host;

    private String port;

    @Bean
    public RedissonClient setRedisson() {
        Config config = new Config();
        config.useSingleServer().setAddress(String.format(REDIS_ADDRESS, this.host, this.port));
        return Redisson.create(config);
    }

}
