package com.codve.stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2019/12/8 17:01
 */
class ToListCollectorTest {

    private List<Employee> employees;

    @BeforeEach
    void setUp() {
        employees = new ArrayList<>();
        employees.add(new Employee("james", 24, 2400, 1));
        employees.add(new Employee("Alice", 18, 3600, 2));
        employees.add(new Employee("jimmy", 26, 2400, 1));
        employees.add(new Employee("maria", 32, 2800, 2));
        employees.add(new Employee("candy", 28, 2900, 2));
        employees.add(new Employee("droid", 44, 3200, 1));
    }

    @Test
    void test() {
        List<Employee> result = employees.stream().collect(new ToListCollector<>());
        result.forEach(System.out::println);
    }

}