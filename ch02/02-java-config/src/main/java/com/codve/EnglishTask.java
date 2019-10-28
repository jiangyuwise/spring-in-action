package com.codve;

import org.springframework.stereotype.Component;

/**
 * @author admin
 * @date 2019/10/28 10:51
 */

public class EnglishTask implements AbstractTask {

    private String name = "english";

    @Override
    public String info() {
        return name;
    }
}
