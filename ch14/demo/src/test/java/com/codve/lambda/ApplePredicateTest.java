package com.codve.lambda;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author admin
 * @date 2019/11/22 15:51
 */
class ApplePredicateTest {

    static List<Apple> filter(List<Apple> apples, ApplePredicate predicate) {

        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    static void print(List<Apple> apples, AppleFormat format) {
        for (Apple apple : apples) {
            System.out.println(format.accept(apple));
        }
    }

    @Test
    void test1() {
        Apple apple1 = new Apple("green", 180);
        Apple apple2 = new Apple("red", 120);
        print(Arrays.asList(apple1, apple2), new AppleFancyFormat());
    }

    @Test
    void test2() {
        Apple apple1 = new Apple("green", 180);
        Apple apple2 = new Apple("red", 120);
        List<Apple> apples = new ArrayList<>();
        apples.add(apple1);
        apples.add(apple2);

        List<Apple> greenApples = filter(apples, new AppleGreenColorPredicate());
        greenApples.forEach(e -> System.out.println(e.toString()));

        List<Apple> heavyApples = filter(apples, new AppleHeavyWeightPredicate());
        heavyApples.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    void test3() {
        Apple apple1 = new Apple("green", 180);
        Apple apple2 = new Apple("red", 120);
        List<Apple> apples = new ArrayList<>();
        apples.add(apple1);
        apples.add(apple2);

        List<Apple> heavyApples = filter(apples, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getWeight() > 150;
            }
        });
        System.out.println(heavyApples.size());
    }

    @Test
    void test4() {
        Apple apple1 = new Apple("green", 180);
        Apple apple2 = new Apple("red", 120);
        List<Apple> apples = new ArrayList<>();
        apples.add(apple1);
        apples.add(apple2);

        List<Apple> heavyApples = filter(apples, (apple -> apple.getWeight() > 150));
    }

}