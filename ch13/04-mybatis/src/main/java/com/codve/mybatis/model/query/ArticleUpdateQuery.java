package com.codve.mybatis.model.query;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author admin
 * @date 2019/12/3 16:31
 */
@Data
public class ArticleUpdateQuery {

    @NotNull
    @Min(value = 1)
    private Long id;

    @NotBlank
    private String title;
}
