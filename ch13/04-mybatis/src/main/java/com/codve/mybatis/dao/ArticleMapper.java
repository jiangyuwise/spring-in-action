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

    int save(ArticleDO articleDO);

    int deleteById(Long id);

    int update(ArticleDO articleDO);

    ArticleDO findById(Long id);

    List<ArticleDO> find(ArticleQuery articleQuery);

    int count(ArticleQuery articleQuery);
}
