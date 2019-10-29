package com.codve;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author admin
 * @date 2019/10/29 10:04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=PersonConfig.class)
@ImportResource("classpath:app.properties")
public class PersonTest {

    @Autowired
    private Person person;

    @Test
    public void test() {
        assertNotNull(person);
        person.info();
    }

}