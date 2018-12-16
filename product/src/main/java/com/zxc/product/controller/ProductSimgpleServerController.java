package com.zxc.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO...
 *
 * @author Zhou RunMing
 * @date 2018/12/16
 */
@RestController
@Slf4j
public class ProductSimgpleServerController {

    @GetMapping("/msg")
    public String msg() {
        this.log.info("路由到该服务");
        return "this is a msg";
    }

}
