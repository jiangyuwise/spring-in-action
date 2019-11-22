package com.codve.functional;

import com.codve.lambda.Predicate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author admin
 * @date 2019/11/22 18:46
 */
public class Filter<T> {

    List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();

        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    @Test
    void test1() {
        Filter<Integer> filter = new Filter<>();
        List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        List<Integer> result = filter.filter(numList, e -> e % 2 != 0);
        assertTrue(result.size() > 0);
    }
}
