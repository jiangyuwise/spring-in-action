package com.codve;

/**
 * @author admin
 * @date 2019/12/12 13:25
 */
public class Superman {

    private String name;

    private int age;

    public Superman(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void info() {
        System.out.println("name: " + name + ", age: " + age);
    }
}
