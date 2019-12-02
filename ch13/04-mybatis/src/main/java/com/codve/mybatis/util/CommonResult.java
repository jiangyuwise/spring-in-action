package com.codve.mybatis.util;

import com.codve.mybatis.exception.EX;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

/**
 * @author admin
 * @date 2019/11/28 17:31
 */
@Data
@JsonInclude(Include.NON_NULL)
public class CommonResult<T> {

    private int code;

    private String msg;

    private T data;

    public static CommonResult success() {
        return newInstance(EX.E_0.getCode(), EX.E_0.getMessage());
    }

    public static CommonResult error(EX ex) {
        return newInstance(ex.getCode(), ex.getMessage());
    }

    public static CommonResult error(int code, String msg) {
        return newInstance(code, msg);
    }

    public static CommonResult newInstance(int code, String msg) {
        CommonResult result = new CommonResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(EX.E_0.getCode());
        result.setMsg(EX.E_0.getMessage());
        result.setData(data);
        return result;
    }
}
