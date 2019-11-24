package com.codve.annotation;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/24 17:27
 */
class StudentTest {

    @Test
    void test() throws NoSuchFieldException {
        Class cls = Student.class;
        Field field = cls.getDeclaredField("name");
        Name name = field.getAnnotation(Name.class);
        assertEquals("someone", name.value());
        System.out.println(name.value());
    }

}