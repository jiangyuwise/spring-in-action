package com.codve.lambda;

/**
 * @author admin
 * @date 2019/11/22 15:48
 */
public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
