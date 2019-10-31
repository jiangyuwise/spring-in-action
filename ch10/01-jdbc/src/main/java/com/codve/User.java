package com.codve;

import java.util.Date;

/**
 * @author admin
 * @date 2019/10/27 22:40
 */
public class User {
    private Long id;
    private String name;
    private Date birthday;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void info() {
        String birthdayStr = String.format("%tF %<tT", birthday);
        System.out.println("id: " + id + ", name: " + name + ", birthday: " + birthdayStr);
    }
}
