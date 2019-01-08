package com.zxc.apigateway.security;

import com.zxc.apigateway.security.config.JwtSecurityConfig;
import com.zxc.apigateway.security.domain.JwtAuthenticationToken;
import com.zxc.apigateway.utils.CookieUtil;
import com.zxc.apigateway.utils.jwt.JWTHelper;
import io.jsonwebtoken.JwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.zxc.apigateway.constant.CookieConstant.JWT_TOKEN;

/**
 * Jwt Security使用时需要的的JwtFilter
 * @see JwtSecurityConfig
 *
 * @author Zhou RunMing
 * @date 2019/1/6
 */
public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter{


    public JwtAuthenticationTokenFilter() {
        super("/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String jwtToken = CookieUtil.get(httpServletRequest, JWT_TOKEN);
        if (StringUtils.isEmpty(jwtToken)) {
            throw new JwtException("jwt token is missing");
        }
        try {
            JWTHelper.parserToken(jwtToken);
        } catch (Exception e) {
            throw new JwtException(e.getMessage());
        }
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(jwtToken);
        return getAuthenticationManager().authenticate(jwtAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
