package com.codve.mybatis.dao;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * @author admin
 * @date 2019/12/2 11:59
 */
public class StringJoinerTest {

    @Test
    void test1() {
        StringJoiner joiner = new StringJoiner(", ");
        joiner.add("hello").add("world");
        System.out.println(joiner.toString());
    }

    @Test
    void test2() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        joiner.add("Anna").add("Jimmy").add("Bob");
        System.out.println(joiner.toString());  // [Anna, Jimmy, Bob]
    }

    @Test
    void test3() {
        List<String> numbers = Arrays.asList("Anna", "Jimmy", "Bob");
        String result = numbers.stream()
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(result);
    }

    @Test
    void test4() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        String result = numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(result);
    }
}
