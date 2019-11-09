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
     * 外部有事务, article 抛出异常
     * article 插入失败
     * @param article article
     * @throws RuntimeException IllegalTransactionStateException
     */
    @Override
    @Transactional
    public void save1(Article article) throws RuntimeException{
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
