package com.mit.online_voting.service;

import com.mit.online_voting.model.User;
import com.mit.online_voting.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(User user) {
        // ✅ Assign default role if not provided by frontend
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("VOTER");
        }

        // ✅ Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // ✅ Save user
        userRepository.save(user);
    }
}
