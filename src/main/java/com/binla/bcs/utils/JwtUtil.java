package com.binla.bcs.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
@Slf4j
public class JwtUtil {
    /**
     * 过期时间30分钟
     */
    public static final long EXPIRE_TIME = 30 * 60 * 1000;

    /**
     * 校验token
     */
    public static boolean verify(String token, String userCode,String secret) {
        try {
            // 根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("userCode", userCode).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.error("token不可用, 原因:{}", e.getMessage());
            return false;
        }
    }

    /**
     * 无需secret从token中获得信息
     */
    public static String getUserCode(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userCode").asString();
        } catch (JWTDecodeException e) {
            log.error("获取token中的用户名失败,原因：{}", e.getMessage());
            return null;
        }
    }

    /**
     * 生成签名
     */
    public static String sign(String userCode,String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        log.info("SignIn, userCode:{}",userCode);
        return JWT.create()
                .withClaim("userCode", userCode)
                .withExpiresAt(date)
                .sign(algorithm);
    }
}
