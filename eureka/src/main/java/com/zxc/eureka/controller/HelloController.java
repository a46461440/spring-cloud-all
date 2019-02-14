package com.zxc.eureka.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zhou RunMing
 * @Date 2019-1-25
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam("id") String id) {
        return "hello" + id;
    }

    /**
     * 当发生了{@link ExceptionHandler}里面定义的错误时,跳转至{@link ResponseStatus}里定义的{@link HttpStatus}
     */
    @ResponseStatus(value = HttpStatus.MULTIPLE_CHOICES, reason = "something wrong")
    @ExceptionHandler(RuntimeException.class)
    public void error() {
        System.out.println( "发生了500错误");
    }

}
