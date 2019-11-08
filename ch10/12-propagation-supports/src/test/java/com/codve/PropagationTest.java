package com.codve;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.IllegalTransactionStateException;
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
     * mandatory 要求必须有事务
     * 此时外部没有事务, 所以会抛出异常.
     */
    @Test
    public void saveTest() {
        assertThrows(IllegalTransactionStateException.class, () -> {
            articleService.save(article);
        });
    }

    /**
     * mandatory
     * 此时有事务, 子方法加入该事务.
     * article 插入成功
     */
    @Test
    public void saveTest2() {
        userService.saveArticle(article);
    }

    /**
     * supports
     * 外部没有事务, 只能按非事务方式执行, 即使抛出异常也无法回滚
     * article 插入成功
     */
    @Test
    public void saveWithExceptionTest() {
        assertThrows(RuntimeException.class, () -> {
            articleService.saveWithException(article);
        });
    }

    /**
     * supports
     * 外部有事务, 子方法加入外部事务.
     * 外部事务回滚, article 插入失败
     */
    @Test
    @Transactional
    public void saveWithExceptionTest2() {
        assertThrows(RuntimeException.class, () -> {
            articleService.saveWithException(article);
        });
    }
}
