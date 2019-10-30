package com.codve;

/**
 * @author admin
 * @date 2019/10/28 17:34
 */

public class Student implements Person{

    private String name;

    private String major;

    public Student(String name, String major) {
        this.name = name;
        this.major = major;
    }

    @Override
    public void info() {
        System.out.println("name: " + name + ", major: " + major);
    }
}
