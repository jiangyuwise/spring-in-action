package com.codve;

import lombok.Data;

import java.util.Date;

/**
 * @author admin
 * @date 2019/11/4 11:46
 */
@Data
public class User {

    private long id;

    private String name;

    private long birthday;

    @Override
    public String toString() {
        Date date = new Date(birthday);
        String formatDate = String.format("%tF %<tT", date);
        return "id: " + id + ", name: " + name + ", birthday: " + formatDate;
    }
}
