package com.codve.mybatis.util;

import com.codve.mybatis.exception.EX;
import lombok.Data;

import java.util.Collection;

/**
 * @author admin
 * @date 2019/11/21 15:51
 */
@Data
public class R<T> {

    private int code;

    private String message;

    private Collection<T> data;

    public R() {
    }

    public static R error(int code, String message) {
        R resultBean = new R();
        resultBean.setCode(code);
        resultBean.setMessage(message);
        return resultBean;
    }

    public static R error(EX exceptionCode) {
        R resultBean = new R();
        resultBean.setCode(exceptionCode.getCode());
        resultBean.setMessage(exceptionCode.getMessage());
        return resultBean;
    }

    public static R success() {
        R resultBean = new R();
        resultBean.setCode(0);
        resultBean.setMessage("success");
        return resultBean;
    }

    public static <V> R<V> success(Collection<V> data) {
        R<V> resultBean = new R<>();
        resultBean.setCode(0);
        resultBean.setMessage("success");
        resultBean.setData(data);
        return resultBean;
    }
}
