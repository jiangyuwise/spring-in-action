package com.codve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    @Transactional(propagation = Propagation.REQUIRED)
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public User saveWithArticle(User user, Article article) {
        user = userRepository.save(user);
        article.setUserId(user.getId());
        try {
            articleService.saveWithException(article);
        } catch (RuntimeException e) {
            System.out.println(e.getClass());
        }
        return user;
    }
    @Override
    public List<User> findAll() {
        Iterable<User> userIterable = userRepository.findAll();
        List<User> userList = new ArrayList<>();
        userIterable.forEach(userList::add);
        return userList;
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public Article saveArticle(Article article) {
        return articleService.save(article);
    }
}
