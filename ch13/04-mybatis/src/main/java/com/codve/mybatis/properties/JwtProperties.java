package com.codve.mybatis.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * @author admin
 * @date 2019/12/10 19:27
 */
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String secret;

    private Duration expire;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Duration getExpire() {
        return expire;
    }

    public void setExpire(Duration expire) {
        this.expire = expire;
    }
}
