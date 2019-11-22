package com.codve.functional;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author admin
 * @date 2019/11/22 19:02
 */
public class Printer<T> {

    void print(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }

    @Test
    void test() {
        Printer<Integer> printer = new Printer<>();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        printer.print(list, t -> System.out.println(t));
    }

}
