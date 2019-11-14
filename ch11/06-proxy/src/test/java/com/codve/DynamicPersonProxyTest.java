package com.codve;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/14 13:41
 */
class DynamicPersonProxyTest {

    @Test
    public void test() throws Throwable {
        DynamicPersonProxy proxy = new DynamicPersonProxy(new Employee("James", "developer"));
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Person person = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(),
                new Class[]{Person.class}, proxy);

        person.info();
    }

}