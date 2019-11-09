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
     */
    @Override
    public void save1(User user, Article article) throws RuntimeException{
        userRepository.save(user);
        articleService.save(article);
        throw new RuntimeException();
    }

    /**
     * 外部未开启事务, articleService 单独开启事务
     * articleService 抛出异常, 回滚.
     * user 插入成功, article 插入失败
     * @param user user
     * @param article article
     * @throws RuntimeException runtimeException
     */
    @Override
    public void save2(User user, Article article) throws RuntimeException {
        userRepository.save(user);
        articleService.saveWithException(article);
    }

    /**
     * 外部未开启事务, articleService 单独开启事务
     * articleService 抛出异常, 但自己捕获了
     * user 插入成功, article 插入成功
     * @param user user
     * @param article article
     * @throws RuntimeException runtimeException
     */
    @Override
    public void save3(User user, Article article) throws RuntimeException {
        userRepository.save(user);
        articleService.saveWithCatch(article);
        throw new RuntimeException();
    }

    /**
     * 外部开启事务, articleService 加入外部事务
     * 外部抛出异常, 外部事务回滚
     * 全部插入失败
     * @param user user
     * @param article article
     * @throws RuntimeException runtimeException
     */
    @Override
    @Transactional
    public void save4(User user, Article article) throws RuntimeException {
        userRepository.save(user);
        articleService.save(article);
        throw new RuntimeException();
    }

    /**
     * 外部开启事务, articleService 加入外部事务
     * 内部抛出异常, 外部事务回滚
     * 全部插入失败
     * @param user user
     * @param article article
     * @throws RuntimeException runtimeException
     */
    @Override
    @Transactional
    public void save5(User user, Article article) throws RuntimeException {
        userRepository.save(user);
        articleService.saveWithException(article);
    }

    /**
     * 外部开启事务, articleService 加入外部事务
     * 内部抛出异常, 并捕获了异常
     * 全部插入成功
     * @param user user
     * @param article article
     * @throws RuntimeException runtimeException
     */
    @Override
    @Transactional
    public void save6(User user, Article article) throws RuntimeException {
        userRepository.save(user);
        articleService.saveWithCatch(article);
    }

    /**
     * 只有抛出 runTimeException 及其子类才会回滚
     * 外部开启事务, 外部抛出 Exception
     * 事务不会回滚, user 插入成功.
     * @param user user
     * @throws Exception Exception
     */
    @Override
    @Transactional
    public void save7(User user) throws Exception {
        userRepository.save(user);
        throw new Exception();
    }

    /**
     * 可以指定触发回滚的异常类型, 如 rollbackFor = Exception.class
     * 外部开启事务, 外部抛出 Exception
     * 事务回滚, user 插入失败.
     * @param user user
     * @throws Exception Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save8(User user) throws Exception {
        userRepository.save(user);
        throw new Exception();
    }

    /**
     * 如果是自调用, 则事务不会回滚
     * 调用的是原始对象的方法, 而不是 spring 代理的对象, 因此没有事务
     * user 插入成功
     * @param user user
     * @throws RuntimeException runtimeException
     */
    @Override
    public void save9(User user) throws RuntimeException {
        this.saveWithException(user);
    }

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    /**
     * 子调用的问题可以通过注入一个代理后的对象解决
     * 事务回滚, user 插入失败
     * @param user user
     * @throws RuntimeException runtimeException
     */
    @Override
    public void save10(User user) throws RuntimeException {
        userService.saveWithException(user);
    }
}
