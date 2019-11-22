package com.codve.lambda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author admin
 * @date 2019/11/22 16:48
 */
class GenericPredicateTest {

    private List<Apple> apples;

    static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    @BeforeEach
    void setUp() {
        Apple apple1 = new Apple("green", 160);
        Apple apple2 = new Apple("red", 180);
        Apple apple3 = new Apple("green", 140);
        apples = Arrays.asList(apple1, apple2, apple3);
    }

    @Test
    void test1() {
        List<Apple> heavyApples = filter(apples, e -> e.getWeight() > 150);
        System.out.println(heavyApples.size());
    }

    @Test
    void test2() {
        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return Float.compare(o1.getWeight(), o2.getWeight());
            }
        });

        apples.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    void test3() {
        apples.sort((e1, e2) -> Float.compare(e1.getWeight(), e2.getWeight()));
        apples.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    void test4() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        });
        thread.run();
    }

    @Test
    void test5() {
        Thread thread = new Thread(() -> System.out.println("hello"));
        thread.run();
    }
}
