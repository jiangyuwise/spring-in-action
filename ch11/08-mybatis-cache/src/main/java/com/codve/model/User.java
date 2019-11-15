package com.codve.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @date 2019/11/13 16:10
 */
@Data
public class User implements Serializable {

    private Long id;

    private String name;

    private Long birthday;

    public User() {
    }

    public User(String name, Long birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        Date date = new Date(birthday);
        String birthdayStr = String.format("%tF", date);
        return "id: " + id + ", name: " + name + ", birthday: " + birthdayStr;
    }
}
