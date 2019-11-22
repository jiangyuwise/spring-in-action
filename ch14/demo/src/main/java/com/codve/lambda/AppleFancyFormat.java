package com.codve.lambda;

/**
 * @author admin
 * @date 2019/11/22 16:00
 */
public class AppleFancyFormat implements AppleFormat {
    @Override
    public String accept(Apple apple) {
        String tmp = apple.getWeight() > 150 ? "weight" : "light";
        return "a " + tmp + " " + apple.getColor() + " apple";
    }
}
