package com.codve.mybatis.exception;

/**
 * @author admin
 * @date 2019/11/21 14:51
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("Could not find user: " + id);
    }
}
