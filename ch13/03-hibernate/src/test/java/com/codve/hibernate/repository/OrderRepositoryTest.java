package com.codve.hibernate.repository;

import com.codve.hibernate.model.Coffee;
import com.codve.hibernate.model.CoffeeOrder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author admin
 * @date 2019/11/18 17:11
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class OrderRepositoryTest {

    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    @Autowired
    private CoffeeRepository coffeeRepository;

    private CoffeeOrder order;

    private Coffee coffee;

    @BeforeEach
    void setUp() {
        coffee = coffeeRepository.findById(1L).orElse(null);
        Coffee coffee1 = coffeeRepository.findById(3L).orElse(null);

        order = CoffeeOrder.builder()
                .customer("诸葛亮")
                .items(Arrays.asList(coffee, coffee1))
                .state(CoffeeOrder.OrderState.INIT)
                .build();
    }

    @Test
    public void saveTest() {
        coffeeOrderRepository.save(order);
        log.info(order.toString());
    }

    @Test
    @Transactional
    void findAllTest() {
        coffeeOrderRepository.findAll().forEach(e -> log.info(e.toString()));
    }

    @Test
    @Transactional
    public void topTest() {

        List<CoffeeOrder> orderList = coffeeOrderRepository.findTop3ByOrderByUpdateTimeDescIdAsc();
        assertTrue(orderList.size() > 0);
        orderList.forEach(e -> log.info(e.toString()));
    }

    @Test
    @Transactional
    public void findByCustomerTest() {
        List<CoffeeOrder> orderList = coffeeOrderRepository.findByCustomerOrderById("姜瑜");
        assertTrue(orderList.size() > 0);
        orderList.forEach(e -> log.info(e.toString()));
    }

    @Test
    @Transactional
    public void findByItemTest() {
        List<CoffeeOrder> orderList = coffeeOrderRepository.findByItemsName("espresso");
        assertTrue(orderList.size() > 0);
        orderList.forEach(e -> log.info(e.toString()));
    }
}