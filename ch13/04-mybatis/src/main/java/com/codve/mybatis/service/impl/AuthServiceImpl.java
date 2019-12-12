package com.codve.mybatis.service.impl;

import com.codve.mybatis.exception.EX;
import com.codve.mybatis.filter.JwtProvider;
import com.codve.mybatis.filter.TokenProvider;
import com.codve.mybatis.model.auth.AuthUser;
import com.codve.mybatis.model.auth.UserType;
import com.codve.mybatis.model.data.object.TokenDO;
import com.codve.mybatis.model.data.object.UserDO;
import com.codve.mybatis.model.query.UserLoginQuery;
import com.codve.mybatis.properties.JwtProperties;
import com.codve.mybatis.service.AuthService;
import com.codve.mybatis.service.TokenService;
import com.codve.mybatis.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
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

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

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

    @Override
    public String passwordAuth2(HttpServletRequest request, UserLoginQuery query) {
        UserDO userDO = userService.findByName(query.getName());
        boolean match = passwordEncoder.matches(query.getPassword(), userDO.getPassword());
        if (!match) {
            exception(EX.E_1202);
        }

        String token = TokenProvider.generateToken(userDO.getId());

        TokenDO param = new TokenDO();
        param.setUserId(userDO.getId());

        List<TokenDO> tokenList = tokenService.find(param);

        Long currentTime = System.currentTimeMillis();

        if (tokenList.size() > 0) {
            TokenDO tokenDO = tokenList.get(0);
            tokenDO.setToken(token);
            tokenDO.setCreateTime(currentTime);
            tokenDO.setExpireTime(currentTime + jwtProperties.getExpire().toMillis());
            tokenService.update(tokenDO);
        } else {
            TokenDO tokenDO = new TokenDO();
            tokenDO.setUserId(userDO.getId());
            tokenDO.setDeviceType(1);
            tokenDO.setDeviceCode("201506");
            tokenDO.setAppType(1);
            tokenDO.setIp(request.getRemoteAddr());
            tokenDO.setToken(token);
            tokenDO.setCreateTime(System.currentTimeMillis());
            tokenDO.setExpireTime(System.currentTimeMillis() + jwtProperties.getExpire().toMillis());
            tokenService.save(tokenDO);
        }
        return token;
    }

    @Override
    public boolean verify(TokenDO tokenDO) {
        UserDO userDO = findUserByToken(tokenDO);
        authenticate(userDO);
        return true;
    }

    @Override
    public boolean verify(TokenDO tokenDO, UserType userType) {
        UserDO userDO = findUserByToken(tokenDO);
        UserType realUserType = UserType.getUserType(userDO.getType());
        if (realUserType != userType) {
            exception(EX.E_1203);
        }
        authenticate(userDO);
        return true;
    }

    private UserDO findUserByToken(TokenDO tokenDO) {
        if (!StringUtils.hasText(tokenDO.getToken())) {
            exception(EX.E_1206);
        }
        tokenDO = tokenService.findByToken(tokenDO.getToken());

        if (System.currentTimeMillis() >= tokenDO.getExpireTime()) {
            exception(EX.E_1205);
        }
        return userService.findById(tokenDO.getUserId());
    }

    private void authenticate(UserDO userDO) {
        AuthUser authUser = AuthUser.newInstance(userDO);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authUser, null, authUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
