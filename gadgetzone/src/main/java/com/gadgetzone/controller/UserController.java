package com.gadgetzone.controller;

import com.gadgetzone.entity.User;
import com.gadgetzone.service.UserService;
import com.gadgetzone.utils.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    // POST register user
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        // Add password encoding here (this is for registration, so we save the password in encoded form)
        String encodedPassword = PasswordEncoderUtil.encoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userService.saveUser(user);
    }


    // POST login (recommended for handling sensitive data like passwords)
    @PostMapping("/login")
    public ResponseEntity<String> loginUserPost(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");  // Assuming password is part of the request body
        
        Optional<User> userOpt = userService.getUserByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            
            // Verify password using BCrypt
            if (PasswordEncoderUtil.encoder().matches(password, user.getPassword())) {
                return ResponseEntity.ok("Login successful");  // In a real app, you'd generate a JWT or session token here
            }
            return ResponseEntity.status(401).body("Invalid password");  // Return 401 if password doesn't match
        }
        
        return ResponseEntity.status(404).body("User not found");  // Return 404 if user isn't found
    }

    @GetMapping("/login/{email}")
    public Optional<User> loginUser(@PathVariable String email) {
        System.out.println("Received login request for: " + email);  // Debug
        Optional<User> user = userService.getUserByEmail(email);
        System.out.println("User found: " + user);  // Debug
        return user;
    }

}
