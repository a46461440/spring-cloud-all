package com.zxc.apigateway.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * TODO...
 *
 * @author Zhou RunMing
 * @date 2019/1/6
 */
public class JwtExeption extends AuthenticationException {
    public JwtExeption(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtExeption(String msg) {
        super(msg);
    }
}
