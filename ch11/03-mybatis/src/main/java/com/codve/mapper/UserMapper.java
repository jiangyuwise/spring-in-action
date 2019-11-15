package com.codve.mapper;

import com.codve.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import static org.apache.ibatis.type.JdbcType.BIGINT;
import static org.apache.ibatis.type.JdbcType.VARCHAR;

/**
 * @author admin
 * @date 2019/11/13 16:06
 */
@Component
public interface UserMapper {
    /**
     * 根据 id 查找用户
     * @param id id
     * @return user
     */
    User findById(@Param("id") Long id);

    /**
     * 添加新用户
     * @param user user
     * @return int
     */
    int insert(User user);
}
