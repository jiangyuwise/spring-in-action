package com.codve;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author admin
 * @date 2019/10/28 10:57
 */
public class StudentTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:app.xml");
        AbstractPerson person = context.getBean("student", Student.class);
        person.work();
        context.close();
    }
}
