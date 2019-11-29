package com.codve.mybatis.exception;

/**
 * @author admin
 * @date 2019/11/21 14:51
 */
public class UserNotFoundException extends CommonException {

    public UserNotFoundException(String message, int code) {
        super(code, message);
    }
}
