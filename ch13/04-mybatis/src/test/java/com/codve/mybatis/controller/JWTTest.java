package com.codve.mybatis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @date 2019/12/8 20:34
 */
public class JWTTest {

    private Key key = new SecretKeySpec("123456".getBytes(),
            SignatureAlgorithm.HS256.getJcaName());

    private ObjectMapper mapper = new ObjectMapper();
    @Test
    void test() throws JsonProcessingException {
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("type", "JWT");

        Date date = new Date(System.currentTimeMillis() + 60 * 1000);
        String dateStr = String.format("%tF %<tT", date);
        Map<String, String> payload = new HashMap<>();
        payload.put("username", "jimmy");
        payload.put("expire_time", dateStr);

        String encode = Jwts.builder().setHeader(header)
                .setPayload(mapper.writeValueAsString(payload))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();

        System.out.println(encode);

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key)
                .parseClaimsJws(encode);
        JwsHeader header1 = claimsJws.getHeader();
        Claims payload1 = claimsJws.getBody();
        System.out.println(header1);
        System.out.println(payload1);
    }
}
