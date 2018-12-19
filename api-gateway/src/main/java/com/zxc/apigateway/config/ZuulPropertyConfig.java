package com.zxc.apigateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author Zhou RunMing
 * @Date 2018-12-19
 */
@Component
public class ZuulPropertyConfig {

    @RefreshScope
    @ConfigurationProperties("zuul")
    @Bean
    @Primary
    public ZuulProperties setZuulProperties() {
        return new ZuulProperties();
    }

}
