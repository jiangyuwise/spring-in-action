package com.codve.mybatis.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * @author admin
 * @date 2019/11/13 16:10
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Positive(message = "用户编号必须大于 0")
    private Long id;

    @NotNull(message = "用户名不能为空")
    @Size(min = 2, max = 32, message = "用户名必须在 2 ~ 32 个字符之间")
    private String name;

    @NotNull
    @Positive(message = "生日必须大于 0")
    private Long birthday;
}
