package com.codve.buffer;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author admin
 * @date 2019/11/22 17:44
 */
public class BufferedReaderTest {

    String processFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            return reader.readLine();
        }
    }

    String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            return p.process(reader);
        }
    }

    @Test
    void test1() throws IOException {
        String str = processFile();
        System.out.println(str);
    }

    @Test
    void test2() throws IOException {
        String str = processFile(r -> r.readLine());
        System.out.println(str);
    }


    @Test
    void test3() throws IOException {
        String str = processFile(BufferedReader::readLine);
        System.out.println(str);
    }


    @Test
    void test4() throws IOException {
        String str = processFile(r -> r.readLine() + "\r\n" + r.readLine());
        System.out.println(str);
    }
}
