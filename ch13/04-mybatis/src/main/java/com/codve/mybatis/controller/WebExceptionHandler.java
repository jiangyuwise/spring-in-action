package com.codve.mybatis.controller;

import com.codve.mybatis.exception.UserNotFoundException;
import com.codve.mybatis.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author admin
 * @date 2019/11/21 16:12
 */
@ControllerAdvice
@Slf4j
public class WebExceptionHandler {

    @ExceptionHandler
    public R userNotFound(UserNotFoundException e) {
        String message = "账号不存在";
        log.error(message, e);
        return R.error(404, message);
    }
}
