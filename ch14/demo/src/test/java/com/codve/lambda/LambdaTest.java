package com.codve.lambda;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

/**
 * @author admin
 * @date 2019/11/22 14:46
 */
class LambdaTest {

    @Test
    void test1() {
        File[] files = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isHidden();
            }
        });

        System.out.println(files.length);
    }

    @Test
    void test2() {
        File[] files = new File(".").listFiles(File::isHidden);
        System.out.println(files.length);
    }

    @Test
    void test3() {

    }
}
