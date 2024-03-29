package com.codve.mybatis.dao;

import com.codve.mybatis.model.data.object.ArticleDO;
import com.codve.mybatis.model.query.ArticleQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/19 17:22
 */
@Repository
public interface ArticleMapper {

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
}
