package com.codve.mybatis.dao;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author admin
 * @date 2019/12/3 17:44
 */
public class ToListTest {

    @Test
    void test() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> rest = numbers.stream().filter(e -> e > 10).collect(Collectors.toList());
        assertNotNull(rest);
        assertEquals(0, rest.size());
    }
}
