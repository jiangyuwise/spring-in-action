package com.codve;

/**
 * @author admin
 * @date 2019/11/14 13:30
 */
public class Student implements Person {

    private String name;

    private String major;

    public Student(String name, String major) {
        this.name = name;
        this.major = major;
    }

    @Override
    public void info() {
        System.out.println("Student: name: " + name + ", major: " + major);
    }
}
