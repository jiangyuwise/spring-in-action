package com.codve.mybatis.filter;

import com.codve.mybatis.exception.EX;
import com.codve.mybatis.model.auth.AuthUser;
import com.codve.mybatis.util.CommonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.codve.mybatis.util.ExceptionUtil.exception;

/**
 * @author admin
 * @date 2019/12/7 16:14
 */
public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtProvider jwtUtil;

    private ObjectMapper objectMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String PREFIX = "Bearer ";

    @Autowired
    public void setJwtUtil(JwtProvider jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = getJwt(request);
            if (StringUtils.hasText(token) && jwtUtil.validate(token)) {
                String redisKey = jwtUtil.getRedisKeyFromToken(token);
                String redisValue = redisTemplate.opsForValue().get(redisKey);
                if (redisValue == null) {
                    exception(EX.E_1205);
                }
                AuthUser user = objectMapper.readValue(redisValue, AuthUser.class);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            String msg = e.getClass().getSimpleName() + ": " + e.getMessage();
            String content = objectMapper.writeValueAsString(
                    CommonResult.error(EX.E_1203.getCode(), msg)
            );
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getOutputStream().println(content);
        }
    }

    private String getJwt(HttpServletRequest request) {
        String token = "";
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(PREFIX)) {
            token =  bearerToken.substring(7);
        }
        return token;
    }
}