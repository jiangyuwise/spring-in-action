package com.codve.mybatis.dao;

import com.codve.mybatis.model.data.object.UserDO;
import com.codve.mybatis.model.query.UserQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/13 19:04
 */
@Repository
public interface UserMapper {

    /**
     * 保存
     * @param userDO userDO
     * @return int
     */
    int save(UserDO userDO);

    /**
     * 根据 id 删除
     * @param id id
     * @return int
     */
    int deleteById(Long id);

    /**
     * 更新
     * @param userDO userDO
     * @return int
     */
    int update(UserDO userDO);

    /**
     * 根据 id 查找一条记录
     * @param id id
     * @return userDO
     */
    UserDO findById(Long id);

    /**
     * 根据条件查找记录
     * @param userQuery 查询条件
     * @return List<UserDO>
     */
    List<UserDO> find(UserQuery userQuery);

    /**
     * 根据条件统计条数
     * @param userQuery 查询条件
     * @return int
     */
    int count(UserQuery userQuery);
}
