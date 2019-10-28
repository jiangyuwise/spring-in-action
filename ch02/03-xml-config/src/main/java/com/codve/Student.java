package com.codve;

/**
 * @author admin
 * @date 2019/10/28 10:40
 */
public class Student implements AbstractPerson {

    private AbstractTask task;

    public Student(AbstractTask task) {
        this.task = task;
    }

    public void setTask(AbstractTask task) {
        this.task = task;
    }

    @Override
    public void work() {
        System.out.println("student work on " + task.info());
    }
}
