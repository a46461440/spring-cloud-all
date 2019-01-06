package com.zxc.apigateway.security;

import com.zxc.apigateway.security.domain.JwtAuthenticationToken;
import com.zxc.apigateway.security.domain.JwtUserDetails;
import com.zxc.apigateway.utils.jwt.JWTHelper;
import com.zxc.apigateway.utils.jwt.JwtInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Jwt Security验证器
 * @see  com.zxc.apigateway.config.JwtSecurityConfig
 *
 * @author Zhou RunMing
 * @date 2019/1/6
 */
@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;
        String jwtToken = jwtAuthenticationToken.getToken();
        Jws<Claims> jws;
        try {
            jws = JWTHelper.parserToken(jwtToken);
        } catch (Exception e) {
            throw new JwtException(e.getMessage());
        }
        if (jws == null)
            throw new JwtException("jwt token is incorrect");
        Claims body = jws.getBody();
        JwtInfo jwtInfo = JWTHelper.convertClaimsBody2JwtInfo(body);
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(String.valueOf(jwtInfo.get("role")));
        return  new JwtUserDetails(jwtInfo.getSubject(), jwtInfo.getId(), jwtToken, grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return JwtAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
