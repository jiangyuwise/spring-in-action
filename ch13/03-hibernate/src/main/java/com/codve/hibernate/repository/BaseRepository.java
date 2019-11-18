package com.codve.hibernate.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 该接口不生成代理 bean
 * @author admin
 * @date 2019/11/18 17:39
 */
@NoRepositoryBean
public interface BaseRepository<T, Long> extends PagingAndSortingRepository<T, Long> {

    /**
     * 按更新时间倒序排序, 再按主键升序排序, 取前 3 条
     * @return List<T>
     */
    List<T> findTop3ByOrderByUpdateTimeDescIdAsc();
}
