package com.gadgetzone.service;

import com.gadgetzone.entity.User;
import com.gadgetzone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // Save user to the database
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Get user by email (case-insensitive query)
    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);  // Case-insensitive query
    }
}
