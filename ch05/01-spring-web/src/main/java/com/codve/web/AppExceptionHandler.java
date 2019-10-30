package com.codve.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author admin
 * @date 2019/10/30 16:48
 */
@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(DuplicateMessageException.class)
    public String duplicateMessageHandler() {
        return "error/duplicate";
    }
}
