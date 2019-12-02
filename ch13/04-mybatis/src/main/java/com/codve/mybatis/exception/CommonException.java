package com.codve.mybatis.exception;

/**
 * @author admin
 * @date 2019/11/29 10:24
 */
public class CommonException extends RuntimeException {

    private int code;

    public CommonException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
