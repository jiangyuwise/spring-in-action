package com.codve;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/7 11:41
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByUserId(Long userId);

    @Query("select a from Article a where a.title like %:title%")
    List<Article> findByTitle(@Param("title") String title);
}
