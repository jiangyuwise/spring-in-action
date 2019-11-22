package com.codve.feature1;

/**
 * 在 java8, 接口可以使用 default 声明, 表示默认的实现.
 * @author admin
 * @date 2019/11/22 12:16
 */
public interface Person {

    void info();

    default void work() {

    }
}
