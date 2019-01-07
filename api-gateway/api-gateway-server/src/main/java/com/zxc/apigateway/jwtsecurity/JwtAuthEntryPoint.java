package com.zxc.apigateway.jwtsecurity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * handler error exception when having unauthorized request
 *
 * @author Zhou RunMing
 * @date 2019/1/7
 */
@Component
@Slf4j
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        this.log.error("Unauthorized error. Message - {}", e.getMessage());
        httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Error -> Unauthorized");
    }
}
