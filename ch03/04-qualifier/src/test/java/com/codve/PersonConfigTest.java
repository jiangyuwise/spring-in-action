package com.codve;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author admin
 * @date 2019/10/28 17:39
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = PersonConfig.class)
public class PersonConfigTest {

    @Autowired
    private Group group;

    @Test
    public void test() {
        assertNotNull(group);
        group.info();
    }


}