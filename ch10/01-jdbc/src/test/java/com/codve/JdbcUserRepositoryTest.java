package com.codve;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author admin
 * @date 2019/10/31 13:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UserConfig.class)
public class JdbcUserRepositoryTest {

    @Autowired
    JdbcUserRepository userRepository;

    private DateFormat format;

    private User user;

    @Before
    public void setUp() throws ParseException {
        format = new SimpleDateFormat("yyyy-MM-dd");
        user = new User();
        user.setName("William");
        user.setBirthday(format.parse("2000-10-1"));
    }

    @Test
    public void testSave2() {
        boolean result = userRepository.save2(user);
        assertTrue(result);
    }

    @Test
    public void testSave3() {
        boolean result = userRepository.save3(user);
        assertTrue(result);
    }

    @Test
    public void testSave4() {
        boolean result = userRepository.save4(user);
        assertTrue(result);
    }

    @Test
    public void testSave5() {
        boolean result = userRepository.save5(user);
        assertTrue(result);
        user.info();
    }

    @Test
    public void testBatchSave() throws ParseException {
        User user1 = new User();
        user1.setName("robot1");
        user1.setBirthday(format.parse("2001-10-1"));

        User user2 = new User();
        user2.setName("robot2");
        user2.setBirthday(format.parse("2001-10-2"));

        boolean result = userRepository.batchSave(Arrays.asList(user1, user2));
        assertTrue(result);
    }

    @Test
    public void testCount() {
        assertNotNull(userRepository);

        assertEquals(3, userRepository.count());
    }

    @Test
    public void testFindOne() {
        User user = userRepository.findOne(1);
        assertNotNull(user);

        user.info();
    }

    @Test
    public void testFindOne2() {
        User user = userRepository.findOne2(1);
        assertNotNull(user);

        user.info();
    }

    @Test
    public void testFindAll() {
        List<User> userList = userRepository.findAll();
        assertTrue(userList.size() > 0);

    }

    @Test
    public void testFindByUsername() {
        String username = "Jimmy";
        List<User> userList = userRepository.findByUsername(username);
        System.out.println(userList.size());
        for (User user : userList) {
            user.info();
        }
        assertTrue(userList.size() > 0);

    }


}