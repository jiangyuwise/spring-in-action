package com.codve.hibernate.repository;

import com.codve.hibernate.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author admin
 * @date 2019/11/18 17:03
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class CoffeeRepositoryTest {

    @Autowired
    private CoffeeRepository coffeeRepository;

    private Coffee coffee;

    @BeforeEach
    void setUp() {
        coffee = new Coffee();
        coffee.setName("雀巢");
        coffee.setPrice(Money.of(CurrencyUnit.of("CNY"), 20.0));
    }

    @Test
    public void saveTest() {
        coffeeRepository.save(coffee);
        log.info(coffee.toString());
    }
}