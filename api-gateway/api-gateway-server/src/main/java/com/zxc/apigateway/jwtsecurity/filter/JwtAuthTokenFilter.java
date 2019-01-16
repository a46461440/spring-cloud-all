package com.zxc.apigateway.jwtsecurity.filter;

import com.zxc.apigateway.utils.CookieUtil;
import com.zxc.apigateway.utils.jwt.JWTHelper;
import com.zxc.apigateway.utils.jwt.JwtInfo;
import com.zxc.common.utils.JwtUtil;
import com.zxc.apigateway.feign.client.UserClient;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.zxc.apigateway.constant.CookieConstant.JWT_TOKEN;

/**
 * 每次请求都经过该filter
 *
 * @author Zhou RunMing
 * @date 2019/1/7
 */
@Slf4j
@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter{

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = this.getJwt(httpServletRequest);
        if (jwtToken != null && !httpServletRequest.getRequestURI().contains("/favicon.ico")) {
            try {
                Claims body = JWTHelper.parserToken(jwtToken).getBody();
                JwtInfo jwtInfo = JWTHelper.convertClaimsBody2JwtInfo(body);
                Optional<JwtInfo> jwtInfoOptional = Optional.ofNullable(jwtInfo);
                if (!jwtInfoOptional.isPresent())
                    throw new SignatureException("the user is not exit");
                List<String> roles = this.userDetailsService.loadUserByUsername(jwtInfoOptional.get().getSubject())
                        .getAuthorities()
                        .stream()
                        .map(s -> String.format("%s_%s","ROLE", s))
                        .collect(Collectors.toList());
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(body.getSubject(), jwtToken, JwtUtil.convertStringList2GrantedAuthorityList(roles));
                //生成通过认证
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
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
