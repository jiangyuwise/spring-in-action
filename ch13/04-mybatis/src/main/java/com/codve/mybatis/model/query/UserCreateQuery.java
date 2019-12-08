package com.codve.mybatis.model.query;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

/**
 * @author admin
 * @date 2019/11/29 18:20
 */
@Data
public class UserCreateQuery {

    @NotNull(message = "用户名不能为空")
    @Size(min = 2, max = 64, message = "用户名的长度为2 ~ 64个字符")
    private String name;

    @NotNull(message = "密码不能为空")
    @Size(min = 6, max = 32, message = "密码的长度为 6 ~ 32 个字符")
    private String password;

    @PositiveOrZero(message = "生日必须大于等于 0")
    private Long birthday;
}
