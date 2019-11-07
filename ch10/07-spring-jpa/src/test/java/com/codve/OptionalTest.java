package com.codve;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/7 10:08
 */
public class OptionalTest {

    @Test
    public void test1() {
        Optional<Integer> nums = Optional.of(1);
        assertTrue(nums.isPresent()); // 判断是否有值
        int num = nums.get(); // 取值
        System.out.println(num);
    }

    @Test
    public void test2() {
        assertThrows(NoSuchElementException.class, () -> {
            Optional<User> users = Optional.empty();
            assertFalse(users.isPresent()); // false
            User user = null;
            user = users.get();
            System.out.println(user.toString());
        });
    }

    @Test
    public void test3() {
        Optional<Integer> intOptional = Optional.of(1);
        intOptional.ifPresent(System.out::println);
    }

    @Test
    public void test4() {
        Optional<Integer> intOptional = Optional.of(1);
        int num = intOptional.orElse(0);
        assertEquals(1, num);
        if (intOptional.isPresent()) {
            num = intOptional.get();
        } else {
            num = 0;
        }
    }

    @Test
    public void test5() {
        Optional<Integer> intOptional = Optional.empty();
        int num = intOptional.orElseGet(() -> {return 1;});
        assertEquals(1, num);
    }

    @Test
    public void test6() {
        Optional<String> nameOptional = Optional.of("hello");
        Optional<String> upperNameOptional = nameOptional.map(String::toUpperCase);
        String name = upperNameOptional.get();
        System.out.println(name);
    }
}
