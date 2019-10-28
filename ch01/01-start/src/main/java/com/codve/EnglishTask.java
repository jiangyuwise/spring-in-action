package com.codve;

/**
 * @author admin
 * @date 2019/10/28 10:51
 */
public class EnglishTask implements AbstractTask {

    private String name;

    public EnglishTask(String name) {
        this.name = name;
    }

    @Override
    public String info() {
        return name;
    }
}
