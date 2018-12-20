package com.zxc.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author Zhou RunMing
 * @Date 2018-12-20
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixExampleController {


    //    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
//    })//(fallbackMethod = "fallback")
    //服务熔断 熔断时间(circuitBreaker.sleepWindowInMilliseconds)
    //结束后进入半开状态,请求一次,如果还是失败则熔断器继续为开启状态并开启一个熔断时间
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //开启熔断
//            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //每多少次有多少比例(下面设置)
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //熔断比例,达到如上次数的这么多比例后熔断
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //熔断器进入开启状态后持续的时间
//    })
    @HystrixCommand
    @GetMapping("/getProductInfoList")
    public String getProductInfo(@RequestParam("number") Integer number) throws InterruptedException {
        if (number % 2 == 0) {
            return "ok";
        }
        Thread.sleep(3000);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(
                "http://localhost:1101/product/listForOrder",
                Arrays.asList("157875196366160023"),
                String.class).getBody();
    }

    //服务降级
    private String fallback() {
        return "服务已被降级";
    }

    private String defaultFallback() {
        return "默认的服务降级方法";
    }

}
