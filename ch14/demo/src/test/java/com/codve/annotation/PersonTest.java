package com.codve.annotation;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/**
 * @author admin
 * @date 2019/11/24 16:34
 */
class PersonTest {

    @Test
    void test1() throws NoSuchFieldException {
        Class cls = Person.class;
        Field field = cls.getDeclaredField("name");
        Name name = field.getAnnotation(Name.class);
        String value = name.value();
        System.out.println(value);
    }
}