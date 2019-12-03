package com.codve.mybatis.dao;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author admin
 * @date 2019/12/3 14:02
 */
public class OptionalTest {

    @Test
    void test() {
        Optional<Integer> optional = Optional.of(1);
        optional.ifPresent(System.out::println);
    }

    @Test
    void testOf() {
        Optional<Integer> optional = Optional.of(1);
        assertTrue(optional.isPresent()); // 判断是否有值
        int num = optional.get(); // 取值
        System.out.println(num);
    }

    @Test
    void testOfNullable() {
        Optional<Integer> optional = Optional.ofNullable(null);
        assertFalse(optional.isPresent());
    }

    @Test
    void testEmpty() {
        Optional<Integer> optional = Optional.empty();
        assertFalse(optional.isPresent());
    }

    @Test
    void testIfPresent() {
        Optional<Integer> optional = Optional.of(1);
        optional.ifPresent(System.out::println);
    }

    @Test
    void testFilter() {
        Optional<Integer> optional = Optional.of(1);
        Optional<Integer> result = optional.filter(e -> e > 10);
        assertFalse(result.isPresent());

        optional = Optional.of(11);
        result = optional.filter(e -> e > 10);
        result.ifPresent(System.out::println);
    }

    @Test
    void testMap() {
        Optional<Integer> optional = Optional.of(1);
        Optional<Integer> result = optional.map(e -> e * 10);
        System.out.println(result.get());
    }

    @Test
    void testFlatMap() {
        Optional<Integer> optional = Optional.of(1);
        Optional<Integer> result = optional.flatMap(e -> Optional.of(e * 10));
        System.out.println(result.get());
    }

    @Test
    void testOrElse() {
        Optional<Integer> optional = Optional.empty();
        int result = optional.orElse(1);
        assertEquals(1, result);
    }

    @Test
    void testOrElseGet() {
        Optional<Integer> optional = Optional.empty();
        int result = optional.orElseGet(() -> 1);
        assertEquals(1, result);
    }

}
