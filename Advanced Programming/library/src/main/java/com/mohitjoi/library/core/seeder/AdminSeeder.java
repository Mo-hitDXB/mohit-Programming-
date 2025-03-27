package com.mohitjoi.library.core.seeder;

import com.mohitjoi.library.core.model.User;
import com.mohitjoi.library.core.repository.UserRepository;
import com.mohitjoi.library.core.type.Role;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminSeeder implements CommandLineRunner {

    @NonNull
    private final UserRepository userRepository;

    @NonNull
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        String adminEmail = "admin@gmail.com";

        if (userRepository.findByEmail(adminEmail).isEmpty()) {
            User admin = new User();
            admin.setFirstName("admin");
            admin.setLastName("admin");
            admin.setEmail(adminEmail);
            admin.setRole(Role.ADMIN);
            admin.setPhone("000000000");
            admin.setPassword(passwordEncoder.encode("Test@123"));

            userRepository.save(admin);
        }
    }
}
