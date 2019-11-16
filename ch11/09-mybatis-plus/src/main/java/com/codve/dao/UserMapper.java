package com.codve.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codve.model.User;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/16 11:44
 */
public interface UserMapper extends BaseMapper<User> {


    /**
     * 查找所有用户
     * @return List<User>
     */
    List<User> selectAll();
}
