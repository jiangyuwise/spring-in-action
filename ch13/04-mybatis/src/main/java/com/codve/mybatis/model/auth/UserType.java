package com.codve.mybatis.model.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * @author admin
 * @date 2019/12/9 15:32
 */
public enum UserType {

    // 管理员
    ADMIN(1, "ROLE_ADMIN"),

    // 普通用户
    USER(2, "ROLE_USER"),

    // 测试用户
    TEST(3, "ROLE_TEST");

    private Integer type;

    private String role;

    UserType(Integer type, String role) {
        this.type = type;
        this.role = role;
    }

    public static UserType getUserType(Integer type) {
        if (type == 1) {
            return ADMIN;
        } else if (type == 2) {
            return USER;
        } else {
            return TEST;
        }
    }

    public static GrantedAuthority createAuthority(UserType userType) {
        return new SimpleGrantedAuthority(userType.getRole());
    }

    public Integer getType() {
        return type;
    }

    public String getRole() {
        return role;
    }
}
