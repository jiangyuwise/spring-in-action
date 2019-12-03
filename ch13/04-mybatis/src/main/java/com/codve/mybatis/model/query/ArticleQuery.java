package com.codve.mybatis.model.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * @author admin
 * @date 2019/11/27 13:29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ArticleQuery extends PageQuery{

    @Min(value = 1)
    private Long id;

    @Min(value = 1)
    private Long userId;

    private String title;

    @Min(value = 1)
    private Long start;

    @Min(value = 1)
    private Long end;

    private List<@Min(value = 1) Long> userIds;

    @Range(min = 1, max = 3)
    private Integer orderBy;
}
