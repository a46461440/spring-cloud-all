package com.zxc.apigateway.utils.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class JWTHelperTest{

    @Test
    public void testTokenParseAndGenerate() throws Exception {
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId("1");
        jwtInfo.setSubject("lll");
        jwtInfo.setExpiration(DateTime.now().plusSeconds(30).toDate());
        jwtInfo.putIfAbsent("age", "23");
        byte[] primaryKey = RsaKeyHelper.generatePrivateKey("aaaa");
        byte[] publicKey = RsaKeyHelper.generatePublicKey("aaaa");
        String token = JWTHelper.generateToken(jwtInfo,primaryKey);
        this.log.info(token);
        Jws<Claims> claims = JWTHelper.parserToken(token, publicKey);
        this.log.info(claims.toString());
        this.log.info(claims.getBody().toString());
        this.log.info(claims.getHeader().toString());
        this.log.info(claims.getSignature().toString());
    }

}