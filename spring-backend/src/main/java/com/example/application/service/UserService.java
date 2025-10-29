java
package com.example.application.service;

import com.example.application.model.User;
import com.example.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUserBio(Long id, String bio) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setBio(bio);
            userRepository.save(user);
        }
        return user;
    }
}