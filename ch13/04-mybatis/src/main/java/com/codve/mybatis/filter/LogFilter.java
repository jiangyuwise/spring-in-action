package com.codve.mybatis.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author admin
 * @date 2019/12/7 15:51
 */
@Slf4j
@WebFilter(urlPatterns = "/*", filterName = "logFilter")
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.warn("receive request");
        long start = System.currentTimeMillis();
        chain.doFilter(request, response);
        log.warn("finish request, cost " + (System.currentTimeMillis() - start) + " ms");
    }
}
