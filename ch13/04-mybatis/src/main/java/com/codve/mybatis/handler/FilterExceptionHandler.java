package com.codve.mybatis.handler;

import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * 处理过滤器抛出的异常
 * @author admin
 * @date 2019/12/11 17:01
 */
public class FilterExceptionHandler {

    public void handle(HttpServletResponse response, String msg) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter out = response.getWriter();
        out.println(msg);
    }
}
