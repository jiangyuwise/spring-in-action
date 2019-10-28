package com.codve;

/**
 * @author admin
 * @date 2019/10/28 17:32
 */
public class Employee implements Person {

    private String name;
    private String position;

    public Employee(String name, String position) {
        this.position = position;
        this.name = name;
    }

    @Override
    public void info() {
        System.out.println("position: " + position + ", name: " + name);
    }
}
