package com.zxc.apigateway.jwtsecurity;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.zxc.apigateway.jwtsecurity.commons.JwtAccessConstant.*;

/**
 * @author Zhou RunMing
 * @Date 2019-1-8
 */
@Component
@Slf4j
public class JwtAccessProblemHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        if (e.getClass().isAssignableFrom(ExpiredJwtException.class)) {
            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), EXPIRED_WARNING);
        } else if (e.getClass().isAssignableFrom(AuthenticationCredentialsNotFoundException.class)) {
            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), ROLE_NOT_FOUNT_WARNING);
        } else if (e.getClass().isAssignableFrom(InsufficientAuthenticationException.class)) {
            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), CREDENTIAL_NOT_TRUSTED_WARNING);
        } else {
            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        }
        this.log.error(e.toString());
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        String requestUri = httpServletRequest.getRequestURI();
        httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), String.format(UNAUTHORIZED_WARNING, requestUri));
    }
}
