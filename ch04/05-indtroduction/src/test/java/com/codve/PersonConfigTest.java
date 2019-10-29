package com.codve;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author admin
 * @date 2019/10/29 14:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersonConfig.class)
public class PersonConfigTest {

    @Autowired
    Person person;

    @Test
    public void test() {
        assertNotNull(person);

        person.work();

        IsDeveloper isDeveloper = (IsDeveloper) person;

        System.out.println(isDeveloper.isDeveloper());
        assertTrue(isDeveloper.isDeveloper());
    }
}