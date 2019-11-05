package com.codve;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author admin
 * @date 2019/11/4 11:46
 */
@Data
public class User {

    private Long id;

    private String name;

    private long birthday;

    @Override
    public String toString() {
        Date date = new Date(birthday);
        String formatDate = String.format("%tF", date);
        return "id: " + id + ", name: " + name + ", birthday: " + formatDate;
    }
}
