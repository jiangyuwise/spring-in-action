package com.codve;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author admin
 * @date 2019/11/4 11:46
 */
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_birthday")
    private long birthday;

    @Override
    public String toString() {
        Date date = new Date(birthday);
        String formatDate = String.format("%tF", date);
        return "id: " + id + ", name: " + name + ", birthday: " + formatDate;
    }
}
