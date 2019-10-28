package com.codve;

/**
 * TODO 编译未通过, 使用${} 获取的是 string, 无法转换为 int
 * @author admin
 * @date 2019/10/28 19:30
 */
public class Person {

    private String name;

    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void info() {
        System.out.println("name: " + name + ", age: " + age);
    }

}
