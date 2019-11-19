package com.codve.mybatis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codve.mybatis.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/18 18:50
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 一个复杂的查找功能
     * @param user
     * @param start
     * @param end
     * @param ids
     * @param orderBy
     * @return
     */
    List<User> selectComplex(@Param("user") User user,
                             @Param("start") Long start,
                             @Param("end") Long end,
                             @Param("ids") List<Long> ids,
                             @Param("orderBy") Integer orderBy);
}
