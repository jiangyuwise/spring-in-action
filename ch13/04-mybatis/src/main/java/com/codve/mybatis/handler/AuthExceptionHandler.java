package com.codve.mybatis.handler;

import com.codve.mybatis.exception.EX;
import com.codve.mybatis.util.CommonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * 如果授权失败, 则返回错误信息
 * @author admin
 * @date 2019/12/8 21:46
 */
@Slf4j
public class AuthExceptionHandler implements AuthenticationEntryPoint {

    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.warn("{} - {}", e.getClass().getName(), e.getMessage());

        String content = objectMapper.writeValueAsString(CommonResult.error(EX.E_1203));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter out = response.getWriter();
        out.println(content);
    }
}
