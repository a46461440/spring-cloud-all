package com.zxc.swaggerconfig.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO...
 *
 * @author Zhou RunMing
 * @date 2018/12/23
 */
@RestController
@Api
@RequestMapping("/swagger")
public class TestSwaggerController {

    @ApiOperation(value = "普通测试swagger",notes = "输出hello world")
    @GetMapping("/hello")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hello", value = "输出内容", required = true, dataType = "String", paramType = "query")
    })
    public String sayHello(@ApiParam(value = "输出参数", defaultValue = "hello") @RequestParam("hello") String hello) {
        return hello;
    }

}
