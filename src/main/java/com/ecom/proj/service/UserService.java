package com.ecom.proj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.proj.model.User;
import com.ecom.proj.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public void addUpdUser(User user) {
        userRepository.save(user);
    }

    public void delCategory(Integer id) {
        userRepository.deleteById(id);
    }
}
