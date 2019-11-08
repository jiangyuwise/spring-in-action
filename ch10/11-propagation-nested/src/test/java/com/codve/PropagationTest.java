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
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        article.setUserId(3L);
        article.setTitle("Life is hard");
        article.setCreateTime(System.currentTimeMillis());
    }

    /**
     * 外部方法没有开启事务
     * user 和 article 会开启各自的事务
     * user 和 article 都插入成功
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
     * 外部方法没有开启事务, 子方法创建各自的事务
     * user 插入成功, article 插入失败
     */
    @Test
    public void test2() {
        assertThrows(RuntimeException.class, () -> {
            userService.save(user);
            articleService.saveWithException(article);
        });
    }

    /**
     * 外部开启事务
     * 子方法创建嵌套事务
     */
    @Test
    @Transactional(propagation = Propagation.NESTED)
    public void userSaveTest1() {
        userService.save(user);
    }

    /**
     * 外部开启事务
     * 子方法创建嵌套事务
     * 外部事务回滚, 嵌套事务也回滚
     * user 插入失败
     */
    @Test
    @Transactional(propagation = Propagation.NESTED)
    public void userSaveTest() {
        assertThrows(RuntimeException.class, () -> {
            userService.save(user);
            throw new RuntimeException();
        });
    }

    /**
     * 外部有事务
     * 子方法使用外部事务
     * 外部事务回滚, user, article都插入失败
     */
    @Test
    @Transactional(propagation = Propagation.NESTED)
    public void test3() {
        assertThrows(RuntimeException.class, () -> {
            userService.save(user);
            articleService.save(article);
            throw new RuntimeException();
        });
    }

    /**
     * 内部方法抛出异常, 导致外部事务回滚
     * user, article 都插入失败
     */
    @Test
    @Transactional(propagation = Propagation.NESTED)
    public void test4() {
        assertThrows(RuntimeException.class, () -> {
            userService.save(user);
            articleService.saveWithException(article);
        });
    }

    /**
     * 外部开启事务
     * 子方法创建嵌套事务
     * user 插入成功, article 插入失败
     * 内部回滚, 外部不回滚
     */
    @Test
    @Transactional(propagation = Propagation.NESTED)
    public void test5() {
        try {
            articleService.saveWithException(article);
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }

    @Test
    public void test6() {
        userService.saveWithArticle(user, article);
        assertTrue(user.getId() > 0);
        System.out.println(user.toString());
    }


}
