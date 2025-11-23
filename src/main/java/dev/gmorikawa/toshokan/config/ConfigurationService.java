package dev.gmorikawa.toshokan.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.domain.user.User;
import dev.gmorikawa.toshokan.domain.user.UserRepository;

@Service
public class ConfigurationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public ConfigurationService(
            UserRepository repository,
            PasswordEncoder passwordEncoder
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createFirstAdmin(User user) {
        if (repository.count() > 0) {
            throw new IllegalStateException("Admin user can only be created on first configuration.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
}
