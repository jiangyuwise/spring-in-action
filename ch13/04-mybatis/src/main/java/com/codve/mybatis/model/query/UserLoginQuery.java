package com.codve.mybatis.model.query;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author admin
 * @date 2019/12/9 11:34
 */
@Data
public class UserLoginQuery {

    @JsonAlias(value = "username")
    @NotBlank
    private String name;

    @NotBlank
    private String password;
}
