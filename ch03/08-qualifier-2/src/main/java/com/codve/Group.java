package com.codve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author admin
 * @date 2019/10/28 17:38
 */
@Component
public class Group {

    private Person student;

    private Person employee;

    @Autowired
    @Qualifier("student")
    public void setPerson(Person person) {
        this.student = person;
    }

    @Autowired
    @Qualifier("employee")
    public void setEmployee(Person employee) {
        this.employee = employee;
    }

    public void info() {
        student.info();
        employee.info();
    }
}
