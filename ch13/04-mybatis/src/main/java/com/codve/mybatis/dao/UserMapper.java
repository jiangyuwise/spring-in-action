package com.codve.mybatis.dao;

import com.codve.mybatis.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/13 19:04
 */
@Repository
public interface UserMapper {

    int save(User user);

    int deleteById(Long userId);

    int update(User user);

    User findById(Long id);

    List<User> find(@Param("user") User user,
                             @Param("start") Long start,
                             @Param("end")  Long end,
                             @Param("userIds") List<Long> userIds,
                             @Param("orderBy")  Integer orderBy);
}
