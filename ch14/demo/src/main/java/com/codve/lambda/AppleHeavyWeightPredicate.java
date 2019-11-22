package com.codve.lambda;

/**
 * @author admin
 * @date 2019/11/22 15:47
 */
public class AppleHeavyWeightPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
