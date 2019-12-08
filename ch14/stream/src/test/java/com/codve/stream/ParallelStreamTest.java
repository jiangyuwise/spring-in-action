package com.codve.stream;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author admin
 * @date 2019/12/8 17:11
 */
public class ParallelStreamTest {

    @Test
    void test() {
        long start = System.currentTimeMillis();
        Long result = LongStream.rangeClosed(1, 1000_0000)
                .parallel()
                .sum();
        long end = System.currentTimeMillis() - start;
        System.out.println(result);
        System.out.println("cost " + end + " ms");
    }

    @Test
    void test2() {
        long start = System.currentTimeMillis();
        long result = 0;
        for (int i = 1; i <= 1000_0000; i++) {
            result += i;
        }
        long end = System.currentTimeMillis() - start;
        System.out.println(result);
        System.out.println("cost " + end + " ms");
    }

    @Test
    void test3() {
        long start = System.currentTimeMillis();
        Long result = LongStream.rangeClosed(1, 1000_0000)
                .sum();
        long end = System.currentTimeMillis() - start;
        System.out.println(result);
        System.out.println("cost " + end + " ms");
    }
}
