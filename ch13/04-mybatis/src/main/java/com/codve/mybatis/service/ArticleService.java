package com.codve.mybatis.service;

import com.codve.mybatis.model.business.object.ArticleBO;
import com.codve.mybatis.model.data.object.ArticleDO;
import com.codve.mybatis.model.query.ArticleQuery;
import com.codve.mybatis.model.query.UserQuery;
import com.codve.mybatis.util.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/19 17:22
 */
@Service
public interface ArticleService {

    /**
     * 保存 articleDO
     * @param articleDO articleDO
     * @return int
     */
    int save(ArticleDO articleDO);

    /**
     * 根据 id 删除
     * @param id id
     * @return int
     */
    int deleteById(Long id);

    /**
     * 更新一条记录
     * @param articleDO articleDO
     * @return int
     */
    int update(ArticleDO articleDO);

    /**
     * 根据 id 查找记录
     * @param id id
     * @return articleDO
     */
    ArticleDO findById(Long id);

    /**
     * 按条件查找 articleDO
     * @param articleQuery 查询条件
     * @return List<ArticleDO>
     */
    List<ArticleDO> find(ArticleQuery articleQuery);

    /**
     * 按条件查找 articleDO 的数量
     * @param articleQuery 查询条件
     * @return int
     */
    int count(ArticleQuery articleQuery);


    /**
     * 根据条件查找用户及其文章
     * @param userQuery 查询条件
     * @return List<ArticleBO>
     */
    PageResult<ArticleBO> unionFind(UserQuery userQuery);

}
