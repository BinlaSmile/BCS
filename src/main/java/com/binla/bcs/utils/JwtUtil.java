package com.binla.bcs.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.binla.bcs.core.properties.JwtProperties;
import com.binla.bcs.domain.constants.SecurityConstant;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtil {

    /**
     * 校验token
     */
    public static boolean verify(String token, String userCode,String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).withClaim(SecurityConstant.JWT_TOKEN_USER, userCode).build();
        verifier.verify(token);
        return true;
    }

    /**
     * 生成签名
     */
    public static String sign(String userCode,String secret) {
        Date date = new Date(System.currentTimeMillis() + JwtProperties.getTokenExpireTime() * 60 * 1000L);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        log.info("SignIn, userCode:{}",userCode);
        return JWT.create()
                .withClaim(SecurityConstant.JWT_TOKEN_USER, userCode)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    public static Claim getClaim(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(claim);
        } catch (JWTDecodeException e) {
            log.error("获取token中的Claim失败,原因：{}", e.getMessage());
            return null;
        }
    }
    public static Map<String, Claim> getClaims(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaims();
        } catch (JWTDecodeException e) {
            log.error("获取token中的Claims失败,原因：{}", e.getMessage());
            return null;
        }
    }
    public static Long getExpireTime(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(SecurityConstant.JWT_TOKEN_EXPIRE).asLong();
        } catch (JWTDecodeException e) {
            log.error("获取token中的过期时间失败,原因：{}", e.getMessage());
            return null;
        }
    }
    public static String getUserCode(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(SecurityConstant.JWT_TOKEN_USER).asString();
        } catch (JWTDecodeException e) {
            log.error("获取token中的用户名失败,原因：{}", e.getMessage());
            return null;
        }
    }


}
