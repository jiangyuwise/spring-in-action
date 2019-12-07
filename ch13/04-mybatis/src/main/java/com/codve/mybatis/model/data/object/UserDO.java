package com.codve.mybatis.model.data.object;

import lombok.Data;

/**
 * @author admin
 * @date 2019/11/26 15:44
 */
@Data
public class UserDO {

    private Long id;

    private String name;

    private String password;

    private Long birthday;
}
