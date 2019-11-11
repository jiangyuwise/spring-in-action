package com.codve;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/10 18:36
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

}
