package com.codve.mybatis.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author admin
 * @date 2019/11/13 16:10
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private Long id;

    private String name;

    private Long birthday;
}
