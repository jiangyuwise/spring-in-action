package com.codve.mybatis.filter;

import com.codve.mybatis.exception.EX;
import com.codve.mybatis.handler.FilterExceptionHandler;
import com.codve.mybatis.model.data.object.TokenDO;
import com.codve.mybatis.service.AuthService;
import com.codve.mybatis.util.CommonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author admin
 * @date 2019/12/11 16:31
 */
@Slf4j
public class TokenFilter extends OncePerRequestFilter {

    private static final String PREFIX = "Bearer ";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthService authService;

    @Autowired
    private FilterExceptionHandler handler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        TokenDO tokenDO = getToken(request);
        try {
            if (StringUtils.hasText(tokenDO.getToken())) {
                authService.verify(tokenDO);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.warn("{} - {}", e.getClass().getName(), e.getMessage());
            String msg = objectMapper.writeValueAsString(CommonResult.error(EX.E_1204.getCode(), e.getMessage()));
            handler.handle(response, msg);
        }
    }

    private TokenDO getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith(PREFIX)) {
            token = token.substring(PREFIX.length());
        }
        TokenDO tokenDO = new TokenDO();
        tokenDO.setToken(token);
        return tokenDO;
    }
}
