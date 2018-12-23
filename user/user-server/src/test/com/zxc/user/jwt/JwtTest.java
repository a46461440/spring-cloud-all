package com.zxc.user.jwt;

import org.bouncycastle.jcajce.provider.asymmetric.rsa.RSAUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * TODO...
 *
 * @author Zhou RunMing
 * @date 2018/12/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {

    private static RSAPrivateKey privateKey;

    private static RSAPublicKey publicKey;

    @Before
    public void init() {

    }

    @Test
    public void testGetJwtToken() {

    }



}
