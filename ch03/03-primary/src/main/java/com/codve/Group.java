package com.codve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;

/**
 * @author admin
 * @date 2019/10/28 17:38
 */
public class Group {

    private Person person;

    public Group(Person person) {
        this.person = person;
    }

    public void info() {
        person.info();
    }
}
