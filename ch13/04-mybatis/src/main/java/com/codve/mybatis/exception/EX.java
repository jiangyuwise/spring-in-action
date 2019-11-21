package com.codve.mybatis.exception;

/**
 * @author admin
 * @date 2019/11/21 16:47
 */
public enum EX {

    // 列表查询失败
    FIND_FAILED(301, "列表查询失败"),
    // 用户未找到
    USER_NOT_FOUND(404, "用户未找到")
    ;

    private int code;

    private String message;

    EX(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
