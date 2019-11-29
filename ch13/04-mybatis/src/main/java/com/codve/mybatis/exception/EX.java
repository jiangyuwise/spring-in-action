package com.codve.mybatis.exception;

/**
 * @author admin
 * @date 2019/11/21 16:47
 */
public enum EX {

    // 成功
    E_0(0, "success"),

    // 列表查询失败
    E_301(301, "添加失败"),

    E_302(302, "删除失败"),

    E_303(303, "修改失败"),

    E_304(304, "无数据"),


    // 用户未找到
    USER_NOT_FOUND(404, "用户未找到"),

    FILE_EMPTY(500, "文件不能为空"),

    e302(302, "添加失败"),
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
