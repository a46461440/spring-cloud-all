package com.zxc.apigateway.jwtsecurity.filter;

import com.zxc.apigateway.utils.CookieUtil;
import com.zxc.apigateway.utils.jwt.JWTHelper;
import com.zxc.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static com.zxc.apigateway.constant.CookieConstant.JWT_TOKEN;

/**
 * 每次请求都经过该filter
 *
 * @author Zhou RunMing
 * @date 2019/1/7
 */
@Slf4j
public class JwtAuthTokenFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = this.getJwt(httpServletRequest);
        if (jwtToken != null && !httpServletRequest.getRequestURI().contains("/favicon.ico")) {
            try {
                Claims body = JWTHelper.parserToken(jwtToken).getBody();
                Authentication authentication = new UsernamePasswordAuthenticationToken(body.getSubject(), jwtToken, JwtUtil.convertStringList2GrantedAuthorityList(Arrays.asList("admin")));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (ExpiredJwtException e) {
                this.log.error("jwt token has been expired:{}", e.getMessage());
            } catch (SignatureException e) {
                this.log.error(e.getMessage());
            } catch (Exception e) {
                this.log.error(e.getMessage());
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getJwt(HttpServletRequest request) {
        String jwt = CookieUtil.get(request, JWT_TOKEN);
        return jwt;
    }
}
