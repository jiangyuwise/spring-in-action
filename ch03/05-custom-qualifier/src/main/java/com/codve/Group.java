package com.codve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author admin
 * @date 2019/10/28 17:38
 */
@Component
public class Group {

    private Person person;

    @Autowired
    @EmployeeQualifier
    public void setPerson(Person person) {
        this.person = person;
    }

    public void info() {
        person.info();
    }
}
