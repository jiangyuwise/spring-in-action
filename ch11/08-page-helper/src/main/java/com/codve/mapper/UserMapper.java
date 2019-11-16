package com.codve.mapper;

import com.codve.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author admin
 * @date 2019/11/13 19:04
 */
public interface UserMapper {

    /**
     * 插入新用户, 并返回主键
     * @param user user
     * @return 插入的行数
     */
    int insert(User user);

    /**
     * 插入新用户, 并返回非自增主键
     * @param user user
     * @return 插入的行数
     */
    int insertAfter(User user);

    /**
     * 根据id 查找用户
     * @param id id
     * @return user
     */
    User findById(Long id);

    /**
     * 查找所有用户
     * @return List<User>
     */
    List<User> findAll();

    /**
     * 带分页的查找
     * @param user user
     * @return List<User>
     */
    List<User> find(User user);

    /**
     * 使用 if 拼接动态 sql, if 放在 set 中
     * @param user user
     * @return 受影响的行数
     */
    int update(User user);

    int delete(Long userId);

    Long count();

    List<User> selectChoose(@Param("id") Long id, @Param("name") String name, @Param("birthday") Long birthday);

    List<User> selectWhere(User user);

    int updateSet(User user);

    List<User> selectForeach(@Param("idList") List<Long> idList);

    /**
     * 批量插入数据
     * @param userList List<User>
     * @return int
     */
    int insertForeach(@Param("userList") List<User> userList);

    /**
     * 批量插入数据, 并返回所有主键
     * 要求使用 jdbc 驱动, mysql 数据库
     * @param userList List<User>
     * @return int
     */
    int insertForeach2(@Param("userList") List<User> userList);

    /**
     * 使用 map 更新
     * @param params Map<String, Object>
     * @param id id
     * @return int
     */
    int updateForeach(@Param("params") Map<String, Object> params, @Param("id") Long id);
}
