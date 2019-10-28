package com.codve;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author admin
 * @date 2019/10/28 15:48
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:app2.xml")
public class FruitsTest {

    @Autowired
    private Fruits fruits;

    @Test
    public void test() {
        fruits.info();
    }

}