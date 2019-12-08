package com.codve.stream;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * T 代表元素的基本类型
 * A 表示累加器要处理的类型
 * R 代表最终返回的类型
 * @author admin
 * @date 2019/12/8 16:50
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    // 创建集合操作的起始点
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    // 将元素添加到结果容器
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    // 合并流
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    // 对容器进行最终转换, identity 表示无需转换
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    // 为收集器添加标志
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                IDENTITY_FINISH, CONCURRENT
        ));
    }
}
