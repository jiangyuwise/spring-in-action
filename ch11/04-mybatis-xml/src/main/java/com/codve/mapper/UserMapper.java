package com.codve.mapper;

import com.codve.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    int updateIf(User user);

    int insertIf(User user);

    List<User> selectChoose(@Param("id") Long id, @Param("name") String name, @Param("birthday") Long birthday);

    List<User> selectWhere(User user);

    int updateSet(User user);

    List<User> selectForeach(@Param("idList") List<Long> idList);

    int insertForeach(@Param("userList") List<User> userList);

    int insertForeach2(@Param("userList") List<User> userList);

    int updateForeach(Map<String, Object> map);
}
