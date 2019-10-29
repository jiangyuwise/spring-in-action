package com.codve;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author admin
 * @date 2019/10/29 12:34
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:app.xml")
public class PersonConfigTest {

    @Autowired
    private Person person;

    @Autowired
    private PersonAspect personAspect;

    @Test
    public void test() {
        assertNotNull(person);
        person.work(1);

        person.work(3);

        person.work(2);

        List<Integer> hours = personAspect.getHours();

        assertEquals(3, hours.size());
        for (int hour : hours) {
            System.out.println(hour);
        }
    }
}