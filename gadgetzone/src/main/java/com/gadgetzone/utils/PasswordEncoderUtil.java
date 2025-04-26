package com.gadgetzone.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderUtil {

    public static PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    // Optional: You can also add a method to validate passwords
    public static boolean match(String rawPassword, String encodedPassword) {
        return encoder().matches(rawPassword, encodedPassword);
    }
}
