package com.codve;

/**
 * @author admin
 * @date 2019/10/29 12:32
 */
public class Employee implements Person {
    @Override
    public void work(int hour) {
        System.out.println("working for " + hour + " hours");
    }
}
