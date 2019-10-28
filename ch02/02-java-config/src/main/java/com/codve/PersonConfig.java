package com.codve;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author admin
 * @date 2019/10/28 13:59
 */
@Component
public class PersonConfig {

    @Bean
    public AbstractTask englishTask() {
        return new EnglishTask();
    }

    @Bean
    public AbstractPerson student(AbstractTask task) {
        return new Student(task);
    }
}
