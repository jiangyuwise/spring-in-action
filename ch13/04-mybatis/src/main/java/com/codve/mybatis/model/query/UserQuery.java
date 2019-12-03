package com.codve.mybatis.model.query;

import lombok.*;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2019/11/26 18:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserQuery extends PageQuery{

    private String name;

    @Min(value = 1)
    private Long start;

    @Min(value = 1)
    private Long end;

    private List<@Min(value = 1) Long> userIds = new ArrayList<>();

    private Integer orderBy;
}
