package com.codve.service.impl;

import com.codve.model.User;
import com.codve.repository.slave1.UserRepository;
import com.codve.service.Slave1UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author admin
 * @date 2019/11/17 16:46
 */
@Service
public class Slave1UserServiceImpl implements Slave1UserService {

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
}
