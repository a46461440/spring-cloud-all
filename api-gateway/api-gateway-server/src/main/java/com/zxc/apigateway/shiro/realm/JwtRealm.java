package com.zxc.apigateway.shiro.realm;

import com.zxc.apigateway.shiro.dto.JwtAuthenticationTokenDto;
import com.zxc.apigateway.utils.jwt.JWTHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


/**
 * @author Zhou RunMing
 * @Date 2018-12-26
 */
public class JwtRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtAuthenticationTokenDto;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String payload = (String) principalCollection.getPrimaryPrincipal();
        return null;
    }

    /**
     * 封装成有效的token
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken instanceof JwtAuthenticationTokenDto)
            return null;
        JwtAuthenticationTokenDto jwtAuthenticationTokenDto = (JwtAuthenticationTokenDto) authenticationToken.getCredentials();
        String jwt = jwtAuthenticationTokenDto.getJwt();
        Jws<Claims> jws;
        try {
            jws = JWTHelper.parserToken(jwt);

        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new SimpleAuthenticationInfo("jwt:" + jws.getBody(), jwt, jws.getBody().getSubject());
    }
}
