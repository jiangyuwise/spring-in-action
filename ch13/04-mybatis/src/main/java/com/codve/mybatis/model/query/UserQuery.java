package com.codve.mybatis.model.query;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2019/11/26 18:39
 */
@Data()
@EqualsAndHashCode(callSuper = false)
public class UserQuery extends PageQuery{

    private String name;

    private Long start;

    private Long end;

    private List<Long> userIds = new ArrayList<>();

    private Integer orderBy;
}
