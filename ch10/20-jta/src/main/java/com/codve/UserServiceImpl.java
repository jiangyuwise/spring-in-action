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

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 两个数据库都插入成功
     * @param user user
     * @return user
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public User save(User user) {
        User saved = userRepository.save1(user);
        userRepository.save2(saved);
        return saved;
    }

    /**
     * 开始两个数据库都插入成功.
     * 但方法抛出异常, 最后两个数据库都回滚
     * @param user user
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveWithException(User user) {
        User saved = userRepository.save1(user);
        userRepository.save2(saved);
        throw new RuntimeException();
    }

    /**
     * 第一个数据库正常插入, 第二个数据库插入时抛出异常
     * 最后2个数据库都回滚
     * @param user user
     * @throws RuntimeException runtimeException
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void save2WithException(User user) throws RuntimeException {
        User saved = userRepository.save1(user);
        userRepository.save2WithException(saved);
    }
}
