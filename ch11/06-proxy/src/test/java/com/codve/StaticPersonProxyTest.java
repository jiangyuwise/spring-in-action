package com.codve;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/14 13:32
 */
class StaticPersonProxyTest {

    @Test
    public void test() {
        Student student = new Student("James", "CS");
        StaticPersonProxy proxy = new StaticPersonProxy(student);
        proxy.info();
    }

}