package com.codve.mybatis.dao;

import com.codve.mybatis.model.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/19 17:22
 */
@Repository
public interface ArticleMapper {

    int save(Article article);

    int deleteById(Long id);

    int update(Article article);

    Article findById(Long id);

    List<Article> find(@Param("article") Article article,
                       @Param("start") Long start,
                       @Param("end") Long end,
                       @Param("userIds") List<Long> userIds,
                       @Param("orderBy") Integer orderBy);
}
