package com.codve.mapper;

import com.codve.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/13 19:04
 */
public interface UserMapper {

    @Insert("insert into `user` (`user_name`, `user_birthday`) values (#{name}, #{birthday})")
    int insert(User user);

    @Select("select * from `user` where `user_id` = #{userId}")
    User findById(Long userId);

    @Select("select * from `user`")
    List<User> findAll();

    @Update("update `user` set `user_name` = #{name}, `user_birthday` = #{birthday} where `user_id` = #{userId}")
    int update(User user);

    @Delete("delete from `user` where `user_id` = #{userId}")
    int delete(Long userId);
}
