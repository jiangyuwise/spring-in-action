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
     * 外部开启事务, article 暂停该事务
     * user 插入成功, article 插入成功
     * @param user user
     * @param article article
     */
    @Override
    @Transactional
    public void save1(User user, Article article) {
        userRepository.save(user);
        articleService.save(article);
    }

    /**
     * 外部开启事务, article 暂停该事务
     * 外部方法抛出异常.
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
}
