package com.codve.mybatis.handler;

import com.codve.mybatis.exception.EX;
import com.codve.mybatis.util.CommonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author admin
 * @date 2019/12/9 18:49
 */
@Slf4j
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FilterExceptionHandler handler;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        log.warn("{} - {}", e.getClass().getName(), e.getMessage());
        String msg = objectMapper.writeValueAsString(CommonResult.error(EX.E_1204));
        handler.handle(response, msg);
    }
}
