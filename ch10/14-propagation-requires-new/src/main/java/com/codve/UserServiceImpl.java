package com.codve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author admin
 * @date 2019/11/6 18:28
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ArticleService articleService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void saveWithException(User user) {
        userRepository.save(user);
        throw new RuntimeException();
    }

    /**
     * 外部未开启事务, article 开启单独的事务
     * user 插入成功, article 插入成功
     * @param user user
     * @param article article
     * @throws RuntimeException exception
     */
    @Override
    public void save1(User user, Article article) throws RuntimeException{
        userRepository.save(user);
        articleService.save(article);
        throw new RuntimeException();
    }

    /**
     * 外部开启事务, article 也会开启单独的事务
     * 外部抛出异常, 外部事务回滚; 内部事务不受影响.
     * 内部事务先提交, 外部事务后提交
     * user 插入失败, article 插入成功
     * @param user user
     * @param article article
     * @throws RuntimeException exception
     */
    @Override
    @Transactional
    public void save2(User user, Article article) throws RuntimeException {
        userRepository.save(user);
        articleService.save(article);
        throw new RuntimeException();
    }

    /**
     * 外部开启事务, article 也会开启单独的事务
     * 内部抛出异常, 外部捕获到该异常
     * 内部事务回滚, 外部事务不受影响
     * user 插入成功, article 插入失败
     * @param user user
     * @param article article
     */
    @Override
    @Transactional
    public void save3(User user, Article article) {
        userRepository.save(user);
        try {
            articleService.saveWithException(article);
        } catch (RuntimeException e) {
            System.out.println(e.getClass());
        }
    }
}
