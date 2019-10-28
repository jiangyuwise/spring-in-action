package com.codve;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author admin
 * @date 2019/10/28 10:45
 */
public class StudentTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test1() {
        AbstractTask abstractTask = mock(AbstractTask.class);
        Student student = new Student(abstractTask);
        student.work();
        verify(abstractTask, times(1)).info();
    }
}