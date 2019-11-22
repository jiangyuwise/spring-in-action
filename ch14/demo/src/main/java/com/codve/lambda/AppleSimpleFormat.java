package com.codve.lambda;

/**
 * @author admin
 * @date 2019/11/22 16:02
 */
public class AppleSimpleFormat implements AppleFormat {
    @Override
    public String accept(Apple apple) {
        return "a " + apple.getWeight() + " g apple";
    }
}
