package com.codve.stream;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/30 10:31
 */
class PersonTest {

    @Test
    void test1() throws IOException, ClassNotFoundException {
        File file = new File("person.txt");

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));

        // 序列化
        Person person = new Person();
        person.setName("James");
        person.setAge(22);
        out.writeObject(person);
        out.close();

        // 反序列化
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        Object newPerson = in.readObject();
        in.close();
        System.out.println(newPerson.toString());
    }

    @Test
    void test2() throws IOException, ClassNotFoundException {
        File file = new File("person.txt");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        Exception e = assertThrows(Exception.class, () -> {
            Object person = in.readObject();
            System.out.println(person.toString());
        });
    }

}