package com.codve.feature1;

/**
 * @author admin
 * @date 2019/11/22 12:17
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
        System.out.println("name: " + name + "major: " + major);
    }

    @Override
    public void work() {
        System.out.println(name + "is studying");
    }
}
