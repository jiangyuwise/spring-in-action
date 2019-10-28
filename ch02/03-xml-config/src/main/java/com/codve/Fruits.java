package com.codve;

import java.util.List;

/**
 * @author admin
 * @date 2019/10/28 15:47
 */
public class Fruits {

    private List<String> fruits;

    public Fruits(List<String> fruits) {
        this.fruits = fruits;
    }

    public void info() {
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
    }
}
