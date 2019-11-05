package com.codve;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author admin
 * @date 2019/11/5 11:11
 */
@Data
@Entity
@Table(name = "article")
public class Article {

    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "article_title")
    private String title;

    @Column(name = "create_time")
    private long createTime;

    @Override
    public String toString() {
        Date date = new Date(createTime);
        return "id: " + id
                + ", userId: " + userId
                + ", article_title: " + title
                + ", crete_time: " + String.format("%tF", date);
    }
}
