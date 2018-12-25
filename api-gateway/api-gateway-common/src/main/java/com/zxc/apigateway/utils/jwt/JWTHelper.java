package com.zxc.apigateway.utils.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.BeanUtils;

import static com.zxc.apigateway.utils.jwt.Constant.PASS_WORD;

/**
 * Jwt工具类
 *
 * @author Zhou RunMing
 * @Date 2018-12-24
 */
public class JWTHelper {
    private static RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();

    /**
     * 密钥加密token
     *
     * @param jwtInfo
     * @param priKeyPath
     * @return
     * @throws Exception
     */
    public static String generateToken(JwtInfo jwtInfo, String priKeyPath) throws Exception {
        JwtBuilder jwtBuilder = buildJwtBuilder(jwtInfo);
        String compactJws = jwtBuilder
                .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(priKeyPath))
                .compact();
        return compactJws;
    }

    /**
     * 密钥加密token
     *
     * @param jwtInfo
     * @param priKey
     * @return
     * @throws Exception
     */
    public static String generateToken(JwtInfo jwtInfo, byte priKey[]) throws Exception {
        JwtBuilder jwtBuilder = buildJwtBuilder(jwtInfo);
        String compactJws = jwtBuilder.signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(priKey))
                .compact();
        return compactJws;
    }

    public static String generateToken(JwtInfo jwtInfo) throws Exception {
        byte[] priKey = RsaKeyHelper.generatePrivateKey(PASS_WORD);
        return generateToken(jwtInfo, priKey);
    }

    private static JwtBuilder buildJwtBuilder(JwtInfo jwtInfo) {
        JwtBuilder jwtBuilder = Jwts.builder();
        BeanUtils.copyProperties(jwtInfo, jwtBuilder);
        jwtInfo.entrySet().stream().forEach(property -> {
            jwtBuilder.claim(property.getKey(), property.getValue());
        });
        return jwtBuilder;
    }

    public static Jws<Claims> parserToken(String token) throws Exception {
        byte[] pubKeyPath = RsaKeyHelper.generatePublicKey(PASS_WORD);
        return parserToken(token, pubKeyPath);
    }

    /**
     * 公钥解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parserToken(String token, String pubKeyPath) throws Exception {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(rsaKeyHelper.getPublicKey(pubKeyPath)).parseClaimsJws(token);
        return claimsJws;
    }

    /**
     * 公钥解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parserToken(String token, byte[] pubKey) throws Exception {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(rsaKeyHelper.getPublicKey(pubKey)).parseClaimsJws(token);
        return claimsJws;
    }

    /**
     * 获取token中的用户信息
     *
     * @param token
     * @param pubKeyPath
     * @return
     * @throws Exception
     */
    public static JwtInfo getInfoFromToken(String token, String pubKeyPath) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, pubKeyPath);
        Claims body = claimsJws.getBody();
        JwtInfo jwtInfo = new JwtInfo();
        BeanUtils.copyProperties(body, jwtInfo);
        return jwtInfo;
    }

    /**
     * 获取token中的用户信息
     *
     * @param token
     * @param pubKey
     * @return
     * @throws Exception
     */
    public static JwtInfo getInfoFromToken(String token, byte[] pubKey) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, pubKey);
        Claims body = claimsJws.getBody();
        JwtInfo jwtInfo = new JwtInfo();
        BeanUtils.copyProperties(body, jwtInfo);
        return jwtInfo;
    }
}
