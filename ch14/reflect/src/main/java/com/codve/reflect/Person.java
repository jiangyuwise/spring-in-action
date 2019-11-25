package com.codve.reflect;

/**
 * @author admin
 * @date 2019/11/25 09:36
 */
public class Person {

    private String name;

    private int age;
    
    private String info() {
        return "name: " + name + ", age: " + age;
    }

    private int add(int a, int b) {
        return a + b;
    }
}
