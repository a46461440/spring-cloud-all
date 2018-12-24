package com.zxc.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * Zuul统一跨域配置
 * C -> Cross
 * O -> Origin
 * R -> Resource
 * S -> Share
 *
 * @author Zhou RunMing
 * @Date 2018-12-20
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter setCorsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setMaxAge(300L);//多少秒内如果再次请求就不再验证
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }

}
