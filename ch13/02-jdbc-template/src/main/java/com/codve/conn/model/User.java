package com.codve.conn.model;

import lombok.Data;

/**
 * @author admin
 * @date 2019/11/16 23:08
 */
@Data
public class User {

    private Long id;

    private String name;

    private Long birthday;

    public User() {
    }

    public User(String name, Long birthday) {
        this.name = name;
        this.birthday = birthday;
    }
}
