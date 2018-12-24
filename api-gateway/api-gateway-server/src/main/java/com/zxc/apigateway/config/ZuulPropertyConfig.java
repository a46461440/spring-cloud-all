package com.zxc.apigateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Zuul配置可动态改变配置(动态改变路由规则)
 *
 * @author Zhou RunMing
 * @Date 2018-12-19
 */
@Configuration
public class ZuulPropertyConfig {

    @RefreshScope
    @ConfigurationProperties("zuul")
    @Bean
    @Primary
    public ZuulProperties setZuulProperties() {
        return new ZuulProperties();
    }

}
