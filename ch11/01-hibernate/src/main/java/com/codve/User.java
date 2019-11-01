package com.codve;

import javax.persistence.*;
import java.util.Date;

/**
 * TODO, 对象映射失败了, Date 和时间戳不匹配.
 * @author admin
 * @date 2019/11/1 11:08
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(name ="user_name")
    private String name;

    @Column(name = "user_birthday")
    private Date birthday;

    public User() {
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
