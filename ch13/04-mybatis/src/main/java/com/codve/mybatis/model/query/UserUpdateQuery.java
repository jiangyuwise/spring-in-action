package com.codve.mybatis.model.query;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

/**
 * @author admin
 * @date 2019/12/2 19:19
 */
@Data
public class UserUpdateQuery {

    @NotNull
    @Min(value = 1L)
    private Long id;

    @NotNull(message = "用户名不能为空")
    @Size(min = 2, max = 64, message = "用户名的长度为2 ~ 64个字符")
    private String name;

    @PositiveOrZero(message = "生日必须大于等于 0")
    private Long birthday;
}
