package com.codve.config;

import com.codve.AbstractPerson;
import com.codve.AbstractTask;
import com.codve.EnglishTask;
import com.codve.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使用注解代替 XML 配置
 * @author admin
 * @date 2019/10/28 11:03
 */
@Configuration
public class PersonConfig {

    @Bean
    public AbstractPerson student() {
        return new Student(englishTask());
    }

    @Bean
    public AbstractTask englishTask() {
        return new EnglishTask("English");
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(PersonConfig.class);
        AbstractPerson person = context.getBean("student", Student.class);
        person.work();
        context.close();
    }
}
