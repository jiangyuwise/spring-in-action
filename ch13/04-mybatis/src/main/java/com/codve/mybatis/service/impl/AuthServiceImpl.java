package com.codve.mybatis.service.impl;

import com.codve.mybatis.exception.EX;
import com.codve.mybatis.filter.JwtProvider;
import com.codve.mybatis.model.auth.AuthUser;
import com.codve.mybatis.properties.JwtProperties;
import com.codve.mybatis.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.codve.mybatis.util.ExceptionUtil.exception;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author admin
 * @date 2019/12/10 13:58
 */
@Service
public class AuthServiceImpl implements AuthService {

    private JwtProvider jwtProvider;

    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProperties jwtProperties;

    private StringRedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String passwordAuth(String username, String password) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        authentication = authenticationManager.authenticate(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AuthUser user = (AuthUser) authentication.getPrincipal();
        user.eraseCredentials();
        String key = generateKey();
        String value = "";
        try {
            value = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            exception(EX.E_999.getCode(), e.getMessage());
        }
        redisTemplate.opsForValue().set(key,
                value,
                jwtProperties.getExpire().getSeconds(),
                TimeUnit.SECONDS);
        return jwtProvider.generateToken(key);
    }

    private String generateKey() {
        String stringValue = String.valueOf(System.currentTimeMillis() + new Random().nextInt(1000));
        SecretKey key = new SecretKeySpec(jwtProperties.getSecret().getBytes(UTF_8), "HmacMD5");
        return Hashing.hmacMd5(key).hashString(stringValue, UTF_8).toString();
    }
}
