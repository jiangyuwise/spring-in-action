package com.codve;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author admin
 * @date 2019/11/7 15:30
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class PropagationTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    private User user;

    private Article article;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("Aria");
        user.setBirthday(System.currentTimeMillis());

        article = new Article();
        article.setUserId(3);
        article.setTitle("Life is hard");
        article.setCreateTime(System.currentTimeMillis());
    }

    /**
     * 外部没有事务, 子方法有 propagation.required 事务
     * 此时子方法会创建单独的事务
     */
    @Test
    public void userSaveTest() {
        assertThrows(RuntimeException.class, () -> {
            userService.save(user);
            throw new RuntimeException();
        });
    }

    /**
     * 外部没有事务, 子方法 propagation.required
     * user, article 会创建各自独立的事务
     * user, article 都会插入成功
     */
    @Test
    public void test1() {
        assertThrows(RuntimeException.class, () -> {
            userService.save(user);
            articleService.save(article);
            throw new RuntimeException();
        });
    }

    /**
     * 外部没有事务
     * user, article 会创建各自独立的事务
     * user 插入成功, article 回滚
     */
    @Test
    public void test2() {
        assertThrows(RuntimeException.class, () -> {
            userService.save(user);
            articleService.saveWithException(article);
        });
    }

    /**
     * 外部有事务, 子方法加入外部的事务中, 不重新创建事务
     * 外部事务回滚, user 插入失败
     */
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void UserSaveTest2() {
        assertThrows(RuntimeException.class, () -> {
            userService.save(user);
            throw new RuntimeException();
        });
    }

    /**
     * 外部方法开启事务, 子方法会加入外部事务
     * 外部事务回滚, 全部插入失败.
     */
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void test3() {
        assertThrows(RuntimeException.class, () -> {
            userService.save(user);
            articleService.save(article);
            throw new RuntimeException();
        });
    }

    /**
     * 外部开启事务, 子方法加入外部事务
     * 外部事务回滚, 全部插入失败
     */
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void test4() {
        assertThrows(RuntimeException.class, () -> {
            userService.save(user);
            articleService.saveWithException(article);
        });
    }

    /**
     * 外部有事务
     * 子方法执行时发生异常, 异常被捕获
     * 外部事务回滚, article 插入失败
     */
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void ArticleSaveTest() {
        try {
            articleService.saveWithException(article);
        } catch (RuntimeException e) {
            System.out.println(e.getClass());
        }
    }

    /**
     * 外部开启事务, 子方法加入外部事务
     * 即使内部方法异常被捕获, 外部事务也会回滚
     * user, article 都插入失败
     */
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void test5() {
        userService.save(user);
        try {
            articleService.saveWithException(article);
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }

}
