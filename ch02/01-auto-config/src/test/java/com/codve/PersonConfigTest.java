package com.codve;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author admin
 * @date 2019/10/28 13:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=PersonConfig.class)
public class PersonConfigTest {

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Autowired
    private AbstractTask task;

    @Autowired
    private AbstractPerson person;

    @Test
    public void taskShouldNotBeNull() {
        assertNotNull(task);
    }

    @Test
    public void work() {
        person.work();
        assertEquals("student work on english\n", log.getLog());
    }
}