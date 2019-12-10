package com.codve.mybatis.filter;

import com.codve.mybatis.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * JWT 工具类
 * @author admin
 * @date 2019/12/8 21:46
 */
@Slf4j
public class JwtProvider {

    @Autowired
    private JwtProperties jwtProperties;

    public String generateToken(String redisKey) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + jwtProperties.getExpire().toMillis());

        return Jwts.builder()
                .setSubject(redisKey)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    public String getRedisKeyFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validate(String token) {
        Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token);
        return true;
    }
}