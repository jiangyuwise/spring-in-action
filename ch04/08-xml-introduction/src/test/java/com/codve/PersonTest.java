package com.codve;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author admin
 * @date 2019/10/29 15:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:app.xml")
public class PersonTest {

    @Autowired
    Person person;

    @Test
    public void test() {
        assertNotNull(person);

        IsDeveloper isDeveloper = (IsDeveloper) person;

        System.out.println(isDeveloper.isDeveloper());
        assertTrue(isDeveloper.isDeveloper());
    }
}