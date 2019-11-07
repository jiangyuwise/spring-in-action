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
     * user 和 article 都插入成功
     * 外部方法没有开启事务
     * 外部方法异常不影响内部方法的事务
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
     * user 插入成功, article 插入失败
     * 外部方法没有开启事务
     * propagation.required 注解的方法会新开自己的事务, 且相互独立
     */
    @Test
    public void test2() {
        assertThrows(RuntimeException.class, () -> {
            userService.save(user);
            articleService.saveWithException(article);
        });
    }

    /**
     * user, article 都插入成功
     * 外部方法开启事务, propagation.requires_new注解的内部事务与外部事务互不影响
     */
    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void test3() {
        assertThrows(RuntimeException.class, () -> {
            userService.save(user);
            articleService.save(article);
            throw new RuntimeException();
        });
    }

    /**
     * user 插入成功, article 插入失败
     */
    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void test4() {
        assertThrows(RuntimeException.class, () -> {
            userService.save(user);
            articleService.saveWithException(article);
        });
    }

    /**
     * user 插入成功, article 插入失败
     */
    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void test5() {
        userService.save(user);
        try {
            articleService.saveWithException(article);
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }

}
