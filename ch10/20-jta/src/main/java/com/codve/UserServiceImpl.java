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
     * 两个数据库都插入成功.
     * @param user
     * @param article
     * @throws RuntimeException
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void save1(User user, Article article) throws RuntimeException{
        userRepository.save(user);
        articleService.save(article);
    }

    /**
     * 外部没有事务
     * article 插入成功
     * @param article article
     */
    @Override
    public void save2(Article article){
        articleService.save(article);
    }
}
