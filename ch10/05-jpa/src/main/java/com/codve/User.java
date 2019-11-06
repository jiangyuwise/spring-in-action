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
@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name = "User.findById", query = "select u from User u where u.id = :id")
})
public class User {

    @Id
    @GeneratedValue(generator="userIdGenerator")
    @GenericGenerator(name="userIdGenerator", strategy = "increment")
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
