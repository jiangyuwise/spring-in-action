package com.codve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 使用资源占位符获取环境变量
 * TODO 这个更加失败了. 单元测试中无法加载属性文件, Person 没有默认构造函数
 * @author admin
 * @date 2019/10/29 10:01
 */
@Component
public class Person {

    private String name;

    private String age;

    @Autowired
    public Person(
            @Value("${person.name}") String name,
            @Value("${person.age}") String age) {
        this.name = name;
        this.age = age;
    }

    public void info() {
        System.out.println("name: " + name + ", age: " + age);
    }
}
