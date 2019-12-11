package com.codve.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author admin
 * @date 2019/12/11 10:04
 */
public class UnmodifiedTest {

    @Test
    void test() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> numbers = Collections.unmodifiableList(list);
        Exception e = assertThrows(RuntimeException.class, () -> {
            numbers.add(3);
        });
        System.out.println(e.getClass().getSimpleName());
        System.out.println(e.getMessage());
    }
}
