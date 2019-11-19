package com.codve.mybatis.dao;

import com.codve.mybatis.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author admin
 * @date 2019/11/13 19:04
 */
@Repository
public interface UserMapper {

    /**
     * 插入新用户, 并返回主键
     * @param user user
     * @return 插入的行数
     */
    int insert(User user);

    int deleteById(Long userId);


    /**
     * 使用 if 拼接动态 sql, if 放在 set 中
     * @param user user
     * @return 受影响的行数
     */
    int update(User user);

    /**
     * 根据id 查找用户
     * @param id id
     * @return user
     */
    User findById(Long id);

    Long count();

    int updateSet(User user);

    /**
     * 复杂的搜索
     * @param user
     * @param start
     * @param end
     * @param ids
     * @param orderBy
     * @return
     */
    List<User> selectComplex(@Param("user") User user,
                             @Param("start") Long start,
                             @Param("end")  Long end,
                             @Param("ids") List<Long> ids,
                             @Param("orderBy")  Integer orderBy);
}
