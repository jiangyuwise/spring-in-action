package com.codve.mybatis.dao;

import com.codve.mybatis.model.data.object.UserDO;
import com.codve.mybatis.model.User;
import com.codve.mybatis.model.query.UserQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/13 19:04
 */
@Repository
public interface UserMapper {

    int save(UserDO userDO);

    int deleteById(Long userId);

    int update(UserDO userDO);

    UserDO findById(Long id);

    List<UserDO> find(UserQuery userQuery);
}
