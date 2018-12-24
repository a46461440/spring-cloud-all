package com.zxc.apigateway.config;

import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimitKeyGenerator;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimitUtils;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.DefaultRateLimitKeyGenerator;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义{@link RateLimitKeyGenerator}组件
 *
 * @author Zhou RunMing
 * @Date 2018-12-19
 */
@Component
public class ZuulRateLimitConfig {

    /**
     * 该返回值key是以什么维度区分的约定,比如存入内存{@link ConcurrentHashMap}则以这个key作为key实现限流
     * @param rateLimitProperties
     * @param rateLimitUtils
     * @return
     */
    @Bean
    public RateLimitKeyGenerator setRateLimitKeyGenerator(RateLimitProperties rateLimitProperties, RateLimitUtils rateLimitUtils) {
        return new DefaultRateLimitKeyGenerator(rateLimitProperties, rateLimitUtils) {
            @Override
            public String key(HttpServletRequest request, Route route, RateLimitProperties.Policy policy) {
                return super.key(request, route, policy);
            }
        };
    }

}
