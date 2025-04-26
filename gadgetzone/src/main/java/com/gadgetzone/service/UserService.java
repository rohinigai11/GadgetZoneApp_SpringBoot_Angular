package com.gadgetzone.service;

import com.gadgetzone.entity.User;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);  // Method to save a user
    Optional<User> getUserByEmail(String email);  // Method to get user by email
}
