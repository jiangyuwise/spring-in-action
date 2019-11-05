package com.codve;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author admin
 * @date 2019/11/5 11:11
 */
@Data
public class Article {

    private long id;

    private long userId;

    private String title;

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
