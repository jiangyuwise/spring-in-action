package com.codve.mybatis.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2019/12/3 11:51
 */
public class BeanUtil {

    public static <U, V> List<V> copyList(List<U> source, Class<V> targetClass) {
        if (source == null || source.size() == 0) {
            return null;
        }
        List<V> result = new ArrayList<>();
        source.forEach(e -> {
            V v = null;
            try {
                v = targetClass.newInstance();
                BeanUtils.copyProperties(e, v);
            } catch (InstantiationException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
            result.add(v);
        });
        return result;
    }
}
