package com.codve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author admin
 * @date 2019/10/28 10:40
 */
@Component
public class Student implements AbstractPerson {

    private AbstractTask task;

    @Autowired
    public Student(AbstractTask task) {
        this.task = task;
    }

    @Autowired
    public void setTask(AbstractTask task) {
        this.task = task;
    }

    @Override
    public void work() {
        System.out.println("student work on " + task.info());
    }
}
