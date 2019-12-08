package com.codve.mybatis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author admin
 * @date 2019/12/7 14:56
 */
@Slf4j
public class AdminInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.warn("start transaction");
        Object result = methodProxy.invokeSuper(o, objects);
        log.warn("end transaction");
        return result;
    }
}
