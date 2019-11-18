package com.codve.conn.model;

import lombok.Data;

/**
 * @author admin
 * @date 2019/11/18 12:09
 */
@Data
public class Article {

    private Long id;

    private Long userId;

    private String title;

    private Long createTime;
}
