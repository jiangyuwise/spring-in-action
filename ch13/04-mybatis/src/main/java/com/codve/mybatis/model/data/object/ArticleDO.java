package com.codve.mybatis.model.data.object;

import lombok.Builder;
import lombok.Data;

/**
 * @author admin
 * @date 2019/11/26 16:55
 */
@Data
public class ArticleDO {

    private Long id;

    private Long userId;

    private String title;

    private Long createTime;
}
