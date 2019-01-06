package com.zxc.apigateway.utils.jwt;

import io.jsonwebtoken.impl.DefaultClaims;

import java.io.Serializable;

/**
 * @author Zhou RunMing
 * @Date 2018-12-24
 */
public class JwtInfo extends DefaultClaims implements Serializable {
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
