package com.codve.dao.spring;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codve.model.User;

/**
 * @author admin
 * @date 2019/11/16 23:08
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据 id 查找用户, 并睡眠 300ms
     * @param id id
     * @return user
     */
    User selectById2(Long id);
}
