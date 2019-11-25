package com.codve.reflect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author admin
 * @date 2019/11/25 09:40
 */
class PersonTest {

    @Test
    void test() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Person person = new Person();

        Class cls = Person.class;

        Field name = cls.getDeclaredField("name");
        name.setAccessible(true); // 取消 java 的访问修饰符限制
        name.set(person, "james"); // 访问私有属性

        Field age = cls.getDeclaredField("age");
        age.setAccessible(true);
        age.set(person, 18);

        Method info = cls.getDeclaredMethod("info", null);
        info.setAccessible(true);
        String str = (String) info.invoke(person); // 调用私有方法
        System.out.println(str);

        Method add = cls.getDeclaredMethod("add", int.class, int.class);
        add.setAccessible(true);
        int sum = (Integer) add.invoke(person, 1, 2);
        System.out.println(sum);
    }

}