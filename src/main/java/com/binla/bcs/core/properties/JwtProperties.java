package com.binla.bcs.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private static Integer tokenExpireTime;
    private static Integer refreshExpireTime;

    public static Integer getTokenExpireTime(){
        return tokenExpireTime;
    }
    public void setTokenExpireTime(Integer tokenExpireTime){
        JwtProperties.tokenExpireTime = tokenExpireTime;
    }
    public static Integer getRefreshExpireTime(){
        return refreshExpireTime;
    }
    public void setRefreshExpireTime(Integer refreshExpireTime){
        JwtProperties.refreshExpireTime = refreshExpireTime;
    }
}
