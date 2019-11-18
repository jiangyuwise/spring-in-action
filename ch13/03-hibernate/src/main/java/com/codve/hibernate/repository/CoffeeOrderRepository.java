package com.codve.hibernate.repository;

import com.codve.hibernate.model.CoffeeOrder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author admin
 * @date 2019/11/18 17:11
 */
@Repository
@Transactional
public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder, Long> {

    /**
     * 根据用户名查找订单, 按照 order id 升序排序
     * @param customer 用户名
     * @return List<CoffeeOrder>
     */
    List<CoffeeOrder> findByCustomerOrderById(String customer);

    /**
     * 根据 coffee 名称查找订单
     * @param name name
     * @return List<CoffeeOrder>
     */
    List<CoffeeOrder> findByItemsName(String name);
}
