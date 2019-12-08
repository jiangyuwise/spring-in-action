package com.codve.stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author admin
 * @date 2019/11/29 14:11
 */
class EmployeeTest {

    private List<Employee> employees;

    @BeforeEach
    void setUp() {
        employees = new ArrayList<>();
        employees.add(new Employee("james", 24, 2400, 1));
        employees.add(new Employee("Alice", 18, 3600, 2));
        employees.add(new Employee("jack", 26, 2400, 1));
        employees.add(new Employee("maria", 32, 2800, 2));
        employees.add(new Employee("bob", 38, 1800, 1));
        employees.add(new Employee("david", 20, 2000, 1));
        employees.add(new Employee("candy", 28, 2900, 2));
        employees.add(new Employee("droid", 44, 3200, 1));
    }

    @Test
    void test1() {
        List<String> names = employees.stream()
                .filter(e -> e.getSalary() > 2500)
                .map(Employee::getName)
                .limit(3)
                .collect(toList());
        names.forEach(System.out::println);
    }

    @Test
    void test2() {
        String[] words = {"hello", "world"};
        List<String> chars = Arrays.stream(words)
                .map(e -> e.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        chars.forEach(System.out::println);
    }

    @Test
    void test3() {
        assertTrue(employees.stream().anyMatch(e -> e.getSalary() >= 3600));
    }

    @Test
    void test4() {
        assertFalse(employees.stream().allMatch(e -> e.getSalary() >= 3600));
    }

    @Test
    void test5() {
        assertTrue(employees.stream().noneMatch(e -> e.getSalary() > 3600));
    }

    @Test
    void test6() {
        employees.stream()
                .filter(e -> e.getSalary() > 2000)
                .findAny()
                .ifPresent(System.out::println);
    }

    @Test
    void test7() {
        Double sum = employees.stream()
                .map(Employee::getSalary)
                .reduce(0.0, Double::sum);
        assertTrue(sum > 0);
        System.out.println(sum);
    }

    @Test
    void test8() {
        Double max = employees.stream()
                .map(Employee::getSalary)
                .reduce(0.0, Double::max);
        System.out.println(max);
    }

    @Test
    void test9() {
        List<Employee> sorted = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .collect(toList());

        sorted.forEach(System.out::println);
    }

    @Test
    void test10() {
        List<Employee> sorted = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .collect(toList());

        sorted.forEach(System.out::println);
    }

    @Test
    void test11() {
        double sum = employees.stream().mapToDouble(Employee::getSalary).sum();
        System.out.println(sum);
    }

    @Test
    void test12() {
        DoubleStream doubleStream = employees.stream()
                .mapToDouble(Employee::getSalary);
        Stream<Double> stream = doubleStream.boxed();
    }

    @Test
    void test13() {
        OptionalDouble max = employees.stream()
                .mapToDouble(Employee::getSalary)
                .max();
        max.ifPresent(System.out::println);
    }

    @Test
    void test14() {
        IntStream intStream = IntStream.rangeClosed(1, 100);
    }

    @Test
    void test15() {
        Comparator<Employee> comparator = Comparator.comparingDouble(Employee::getSalary);

        Optional<Employee> max = employees.stream()
                .max(comparator);
        max.ifPresent(System.out::println);
    }

    @Test
    void test16() {
        double ave = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);
        System.out.println(ave);
    }

    @Test
    void test17() {
        String str = employees.stream().map(Employee::getName).collect(joining(", "));
        System.out.println(str);
    }
}