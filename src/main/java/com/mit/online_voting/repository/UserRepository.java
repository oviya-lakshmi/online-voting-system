package com.mit.online_voting.repository;

import com.mit.online_voting.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by username (for login)
    Optional<User> findByUsername(String username);

    // Find user by email (if needed)
    Optional<User> findByEmail(String email);
}
