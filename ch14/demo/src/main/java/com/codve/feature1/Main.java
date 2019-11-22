package com.codve.feature1;

/**
 * @author admin
 * @date 2019/11/22 12:17
 */
public class Main {
    public static void main(String[] args) {

        Student student = new Student("Alice", "CS");
        student.info();
        student.work();

        Employee employee = new Employee("James", "developer");
        employee.info();
    }
}
