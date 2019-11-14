package com.codve;

/**
 * PersonProxy 使用静态代理
 * @author admin
 * @date 2019/11/14 13:28
 */
public class StaticPersonProxy {

    private Person person;

    public StaticPersonProxy(Person person) {
        this.person = person;
    }

    public void info() {
        person.info();
    }
}
