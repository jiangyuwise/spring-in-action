package com.codve.mapper;

import com.codve.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

import static org.apache.ibatis.type.JdbcType.BIGINT;
import static org.apache.ibatis.type.JdbcType.VARCHAR;

/**
 * @author admin
 * @date 2019/11/13 19:04
 */
public interface UserMapper {

    /**
     * 插入新用户, 并返回主键
     * @param user user
     * @return 受影响的行数
     */
    @Insert("insert into `user` (`user_name`, `user_birthday`) values (#{name}, #{birthday})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    /**
     * 插入新用户, 返回非自增主键
     * @param user user
     * @return 受影响的行数
     */
    @Insert("insert into `user` (`user_name`, `user_birthday`) values (#{name}, #{birthday})")
    @SelectKey(
            statement = "select last_insert_id()",
            keyColumn = "user_id",
            keyProperty = "id",
            before = false,
            resultType = Long.class
    )
    int insertAfter(User user);

    /**
     * 设置 resultMap, 全局通用
     * @param userId userId
     * @return user
     */
    @Results(id = "userMap", value = {
            @Result(column = "user_id", property = "id", javaType = Long.class, jdbcType = BIGINT, id = true),
            @Result(column = "user_name", property = "name", javaType = String.class, jdbcType = VARCHAR),
            @Result(column = "user_birthday", property = "birthday", javaType = Long.class, jdbcType = BIGINT)
    })
    @Select("select * from `user` where `user_id` = #{userId}")
    User findById(Long userId);

    /**
     * 使用@SelectProvider 注解的方法
     * @param userId userId
     * @return user
     */
    @SelectProvider(type= UserProvider.class, method="selectById")
    @ResultMap("userMap")
    User findById2(Long userId);

    /**
     * 查找所有的用户
     * @return List<User>
     */
    @ResultMap("userMap")
    @Select("select * from `user`")
    List<User> findAll();

    /**
     * 更新用户
     * @param user user
     * @return 受影响的行数
     */
    @Update("update `user` set `user_name` = #{name}, `user_birthday` = #{birthday} where `user_id` = #{id}")
    int update(User user);

    /**
     * 删除用户
     * @param user user
     * @return 受影响的行数
     */
    @Delete("delete from `user` where `user_id` = #{id}")
    int delete(User user);
}
