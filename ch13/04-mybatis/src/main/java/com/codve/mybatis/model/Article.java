package com.codve.mybatis.model;

import lombok.Data;

/**
 * @author admin
 * @date 2019/11/19 17:21
 */
@Data
public class Article {

    private Long id;

    private Long userId;

    private String title;

    private Long createTime;
}
