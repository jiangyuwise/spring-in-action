package com.codve;

/**
 * @author admin
 * @date 2019/10/29 14:24
 */
public class Employee implements Person {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void info() {
        System.out.println("name: " + name);
    }
}
