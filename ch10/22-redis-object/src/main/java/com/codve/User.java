package com.codve;

import lombok.Data;

import java.io.Serializable;

/**
 * @author admin
 * @date 2019/11/11 19:52
 */
@Data
public class User implements Serializable {

    private String name;

    private int age;

    @Override
    public String toString() {
        return "name: " + name + "age: " + age;
    }
}
