package com.codve;

/**
 * @author admin
 * @date 2019/11/14 13:29
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
        System.out.println("Employee: name: " + name + ", position: " + position);
    }
}
