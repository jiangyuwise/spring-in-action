package com.codve.feature1;

/**
 * @author admin
 * @date 2019/11/22 12:19
 */
public class Employee implements Person {

    private String name;

    private String position;

    public Employee(String name, String position) {
        this.name = name;
        this.position = position;
    }

    @Override
    public void info() {
        System.out.println("name: " + name + ", position: " + position);
    }
}
