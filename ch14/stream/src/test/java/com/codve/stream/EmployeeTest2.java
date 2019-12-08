package com.codve.stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.stream.Collectors.*;

/**
 * @author admin
 * @date 2019/12/8 15:37
 */
public class EmployeeTest2 {

    private List<Employee> employees;

    @BeforeEach
    void setUp() {
        employees = new ArrayList<>();
        employees.add(new Employee("james", 24, 2400, 1));
        employees.add(new Employee("Alice", 18, 3600, 2));
        employees.add(new Employee("jimmy", 26, 2400, 1));
        employees.add(new Employee("maria", 32, 2800, 2));
        employees.add(new Employee("bob", 38, 1800, 1));
        employees.add(new Employee("david", 20, 2000, 1));
        employees.add(new Employee("candy", 28, 2900, 2));
        employees.add(new Employee("droid", 44, 3200, 1));
    }

    @Test
    void test1() {
        Map<Integer, List<Employee>> group = employees.stream()
                .collect(groupingBy(Employee::getSex));

        group.forEach((k, v) -> {
            v.forEach(System.out::println);
            System.out.println();
        });
    }

    @Test
    void test2() {
        Map<Integer, List<Employee>> group = employees.stream().collect(
                groupingBy(e -> {
                    if (e.getSalary() <= 2000) {
                        return 1;
                    } else if (e.getSalary() <= 3000) {
                        return 2;
                    } else {
                        return 3;
                    }
                })
        );
        group.forEach((k, v) -> {
            v.forEach(System.out::println);
            System.out.println();
        });
    }

    @Test
    void test3() {
        Map<Integer, Map<Integer, List<Employee>>> group =
            employees.stream().collect(
                groupingBy(Employee::getSex,
                    groupingBy(e -> {
                        if (e.getSalary() <= 2000) {
                            return 1;
                        } else if (e.getSalary() <= 3000) {
                            return 2;
                        } else {
                            return 3;
                        }
                    })
                )
            );

        group.forEach((k, v) -> {
            v.forEach((kk, vv) -> {
                vv.forEach(System.out::println);
            });
        });
    }

    @Test
    void test4() {
        Map<Integer, Long> group = employees.stream().collect(
                groupingBy(Employee::getSex, counting())
        );

        group.forEach((k, v) -> System.out.println(v));
    }

    @Test
    void test5() {
        Map<Integer, Optional<Employee>> group = employees.stream().collect(
            groupingBy(Employee::getSex,
                maxBy(Comparator.comparingDouble(Employee::getSalary))
            ));

        group.forEach((k, v) -> {
            v.ifPresent(System.out::println);
        });
    }

    @Test
    void test6() {
        Map<Integer, Employee> group = employees.stream().collect(
            groupingBy(Employee::getSex,
                collectingAndThen(
                    maxBy(Comparator.comparingDouble(Employee::getSalary)),
                        Optional::get
                )
            )
        );

        group.forEach((k, v) -> {
            System.out.println(v);
        });
    }

    @Test
    void test7() {
        Map<Integer, Double> group = employees.stream().collect(
            groupingBy(Employee::getSex,
                summingDouble(Employee::getSalary))
        );
        group.forEach((k, v) -> {
            System.out.println(v);
        });
    }

    @Test
    void test8() {
        Map<Boolean, List<Employee>> group = employees.stream().collect(
            partitioningBy(e -> e.getSex() == 1)
        );
        group.forEach((k, v) -> {
            v.forEach(System.out::println);
        });

    }


}
