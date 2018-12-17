package com.zxc.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * TODO...
 *
 * @author Zhou RunMing
 * @date 2018/12/17
 */
@Component
@ConfigurationProperties("girl")
@RefreshScope
@Data
public class GirlConfig {

    private String name;

    private Integer age;

}
