package com.codve;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Proxy;

/**
 * 检验 AOP
 * 配置文件为 app2.xml
 * @author admin
 * @date 2019/10/28 10:57
 */
public class InterceptorTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:app2.xml");
        // 这个地方必须指定类型为 AbstractPerson.class, 不能为 Student.class
        AbstractPerson person = context.getBean("student", AbstractPerson.class);
        person.work();
        context.close();
    }
}
