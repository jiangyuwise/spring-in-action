package com.codve.feature1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/22 20:06
 */
class PersonTest {

    @Test
    void test() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Employee("Alice", "teacher"));
        personList.add(new Employee("James", "developer"));
        personList.forEach(Person::info); //  等同于 e -> e.info();
    }
}