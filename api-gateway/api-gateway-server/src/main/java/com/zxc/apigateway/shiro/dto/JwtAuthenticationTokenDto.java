package com.zxc.apigateway.shiro.dto;

import io.jsonwebtoken.impl.DefaultClaims;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

import java.io.Serializable;

/**
 * @author Zhou RunMing
 * @Date 2018-12-26
 */
@Data
public class JwtAuthenticationTokenDto extends DefaultClaims implements AuthenticationToken, Serializable {

    private String jwt;

    @Override
    public Object getPrincipal() {
        return super.getId();
    }

    @Override
    public Object getCredentials() {
        return this.jwt;
    }

}
