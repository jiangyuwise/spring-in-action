package com.codve;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理
 * @author admin
 * @date 2019/11/14 13:39
 */
public class DynamicPersonProxy implements InvocationHandler {

    private Person person;

    public DynamicPersonProxy(Person person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(person, args);
    }
}
