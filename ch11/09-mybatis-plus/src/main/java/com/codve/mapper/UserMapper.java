package com.codve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codve.model.User;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/16 11:44
 */
public interface UserMapper extends BaseMapper<User> {

//    @Select("select * from `user`")
    List<User> selectAll();
}
