package com.zxc.order.controller.advise;

import com.zxc.order.controller.GirlController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Zhou RunMing
 * @Date 2019-1-3
 */
@RestControllerAdvice(assignableTypes = {GirlController.class})
@Slf4j
public class CommonAdvise {

    @ExceptionHandler(value = {RuntimeException.class})
    public String dealRuntimeException(Exception e) {
        this.log.error(e.getMessage());
        return "sorry, you have trigger a RuntimeException";
    }

}
