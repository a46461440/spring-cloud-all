package com.zxc.apigateway.shiro.macher;


import com.zxc.apigateway.utils.jwt.JWTHelper;
import io.jsonwebtoken.*;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static com.zxc.apigateway.constant.RedisConstant.TOKEN;

/**
 * @author Zhou RunMing
 * @Date 2018-12-26
 */
@Component
public class JwtMatcher implements CredentialsMatcher {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        String jwt = (String) authenticationInfo.getCredentials();
        Jws<Claims> jws;
        try {
            jws = JWTHelper.parserToken(jwt);
        } catch (SignatureException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            throw new AuthenticationException("errJwt"); // 令牌错误
        } catch (ExpiredJwtException e) {
            throw new AuthenticationException("expiredJwt"); // 令牌过期
        } catch (Exception e) {   //未知错误
            throw new AuthenticationException("errJwt");
        }
        if (jws == null) { //未知的错误
            throw new AuthenticationException("unexpectable error");
        }
        Claims claims = jws.getBody();
        String tokenInRemoteCache = this.getTokenFromRemoteCacheByJwtBody(claims);
        if (!StringUtils.isEmpty(tokenInRemoteCache))
            throw new AuthenticationException("expiredJwt"); //令牌在缓存中已经不存在
        return true;
    }

    private String getTokenFromRemoteCacheByJwtBody(Claims claims) {
        return this.stringRedisTemplate.opsForValue().get(String.format(TOKEN, claims.getId()));
    }
}
