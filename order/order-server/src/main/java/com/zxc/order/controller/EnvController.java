package com.zxc.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO...
 *
 * @author Zhou RunMing
 * @date 2018/12/17
 */
@RestController
@RequestMapping("/env")
@RefreshScope
public class EnvController {

    @Value("${env}")
    public String env;

    @GetMapping("/getEnv")
    public String getValue(){
        return this.env;
    }

}
