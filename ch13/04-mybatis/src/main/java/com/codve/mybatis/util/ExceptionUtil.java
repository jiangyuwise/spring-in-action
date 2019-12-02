package com.codve.mybatis.util;

import com.codve.mybatis.exception.CommonException;
import com.codve.mybatis.exception.EX;

/**
 * @author admin
 * @date 2019/11/29 10:30
 */
public class ExceptionUtil {

    public static void exception(EX ex) {
        throw new CommonException(ex.getCode(), ex.getMessage());
    }
}
