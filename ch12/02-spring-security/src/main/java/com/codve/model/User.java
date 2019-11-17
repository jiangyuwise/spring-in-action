package com.codve.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author admin
 * @date 2019/11/16 23:08
 */
@Data
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="user_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_birthday")
    private Long birthday;
}
