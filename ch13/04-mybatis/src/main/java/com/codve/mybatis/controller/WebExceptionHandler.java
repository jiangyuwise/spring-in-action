package com.codve.mybatis.controller;

import com.codve.mybatis.exception.EX;
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
        return R.error(EX.USER_NOT_FOUND);
    }
}
