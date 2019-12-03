package com.codve.mybatis.model.query;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author admin
 * @date 2019/12/3 16:09
 */
@Data
public class ArticleCreateQuery {

    @Min(value = 1)
    @NotNull
    private Long userId;

    @NotBlank
    private String title;
}
