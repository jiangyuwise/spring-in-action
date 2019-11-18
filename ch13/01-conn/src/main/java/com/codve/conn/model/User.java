package com.codve.conn.model;

import lombok.Data;

import java.util.Date;

/**
 * @author admin
 * @date 2019/11/16 23:08
 */
@Data
public class User {

    private Long id;

    private String name;

    private Long birthday;

    @Override
    public String toString() {
        Date date = new Date(birthday);
        String dateStr = String.format("%tF", date);
        return "id: " + id + ", name: " + name + ", birthday: " + dateStr;
    }
}
