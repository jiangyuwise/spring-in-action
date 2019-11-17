package com.codve.service.impl;

import com.codve.model.User;
import com.codve.repository.spring.UserRepository;
import com.codve.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author admin
 * @date 2019/11/17 16:46
 */
@Service
public class SpringUserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public void setUserMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User getById2(Long id) {
        User user = userRepository.selectById2(id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user;
    }
}
