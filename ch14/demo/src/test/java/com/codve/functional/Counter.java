package com.codve.functional;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @author admin
 * @date 2019/11/22 19:26
 */
public class Counter<T, R> {

    public List<R> count(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }

    @Test
    void test() {
        Counter<String, Integer> counter = new Counter<>();
        List<String> strs = Arrays.asList("hello", "world", "how are you?");
        List<Integer> result = counter.count(strs, e -> e.length());
        result.forEach(System.out::println);
    }
}
