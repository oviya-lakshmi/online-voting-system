package com.mit.online_voting.config;

import com.mit.online_voting.model.Candidate;
import com.mit.online_voting.model.User;
import com.mit.online_voting.repository.CandidateRepository;
import com.mit.online_voting.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    
    @Bean
    CommandLineRunner initDatabase(CandidateRepository candidateRepository, 
                                   UserRepository userRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {
            // Initialize candidates if not exists
            if (candidateRepository.count() == 0) {
                candidateRepository.save(new Candidate("Narendra Modi", "BJP", "🪷 Lotus"));
                candidateRepository.save(new Candidate("Rahul Gandhi", "INC", "✋ Hand"));
                candidateRepository.save(new Candidate("Arvind Kejriwal", "AAP", "🧹 Broom"));
                candidateRepository.save(new Candidate("Mamata Banerjee", "TMC", "🌾 Grass Flower"));
                System.out.println("✅ Candidates initialized");
            }
            
            // Initialize admin user if not exists
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@voting.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ADMIN");
                userRepository.save(admin);
                System.out.println("✅ Admin user created (username: admin, password: admin123)");
            }
        };
    }
}