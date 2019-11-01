package com.codve;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @author admin
 * @date 2019/11/1 12:25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UserConfig.class)
public class UserConfigTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testCount() {
        long result = userRepository.count();
        assertEquals(3, result);
    }
}