package com.codve.mapper;

import com.codve.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/13 16:06
 */
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

    /**
     * 查找所有用户
     * @return List<User>
     */
    List<User> findAll();
}
