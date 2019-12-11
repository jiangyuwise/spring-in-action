package com.codve.mybatis.filter;

import com.google.common.hash.Hashing;

import java.util.Random;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author admin
 * @date 2019/12/11 14:42
 */
public class TokenProvider {

    public static String generateToken(Long userId) {
        long time = System.currentTimeMillis();
        long random = new Random().nextInt(10000);
        String msg = String.valueOf(random + userId + time);
        return Hashing.sha256().hashString(msg, UTF_8).toString();
    }
}
