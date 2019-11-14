package com.codve.mapper;

import com.codve.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

import static org.apache.ibatis.type.JdbcType.BIGINT;
import static org.apache.ibatis.type.JdbcType.VARCHAR;

/**
 * @author admin
 * @date 2019/11/13 19:04
 */
public interface UserMapper {

    @Insert("insert into `user` (`user_name`, `user_birthday`) values (#{name}, #{birthday})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Insert("insert into `user` (`user_name`, `user_birthday`) values (#{name}, #{birthday})")
    @SelectKey(
            statement = "select last_insert_id()",
            keyColumn = "user_id",
            keyProperty = "id",
            before = false,
            resultType = Long.class
    )
    int insertAfter(User user);

    @Results(id = "userMap", value = {
            @Result(id = true, column = "user_id", property = "id", javaType = Long.class, jdbcType = BIGINT),
            @Result(column = "user_name", property = "name", javaType = String.class, jdbcType = VARCHAR),
            @Result(column = "user_birthday", property = "birthday", javaType = Long.class, jdbcType = BIGINT)
    })
    @Select("select * from `user` where `user_id` = #{userId}")
    User findById(Long userId);

    @ResultMap("userMap")
    @Select("select * from `user`")
    List<User> findAll();

    @Update("update `user` set `user_name` = #{name}, `user_birthday` = #{birthday} where `user_id` = #{id}")
    int update(User user);

    @Delete("delete from `user` where `user_id` = #{userId}")
    int delete(@Param("userId") Long userId);
}
