package com.codve.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @date 2019/11/30 10:29
 */
@Data
public class Person implements Serializable {

    private static final long serialVersionUID = 2L;

    private String name;

    private int age;

    private Date birthday;
}
