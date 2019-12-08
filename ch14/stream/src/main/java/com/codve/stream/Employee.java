package com.codve.stream;

import lombok.Data;

/**
 * @author admin
 * @date 2019/11/29 14:09
 */
@Data
public class Employee {

    private String name;

    private int age;

    private double salary;

    private int sex;

    public Employee(String name, int age, double salary, int sex) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.sex = sex;
    }
}
