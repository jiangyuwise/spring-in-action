package com.codve.mapper;

import com.codve.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/13 19:04
 */
public interface UserMapper {

    int insert(User user);

    int insertAfter(User user);

    User findById(Long userId);

    List<User> findAll();

    List<User> findByParams(@Param("name") String name, @Param("birthday") Long birthday);

    int update(User user);

    int delete(Long userId);

    Long count();

    List<User> selectIf(@Param("name") String name, @Param("birthday") Long birthday);

}
