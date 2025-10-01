package dev.gmorikawa.toshokan.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfig {

    private final PasswordEncoder passwordEncoder;

    public UserConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    @Bean
    public CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            // User u1 = new User("gmorikawa", "gmorikawa", "gabriel.morikawa@email.com", UserRole.ADMIN, "Gabriel Morikawa");

            // u1.setPassword(passwordEncoder.encode(u1.getPassword()));

            // repository.saveAll(List.of(u1));
        };
    }
}
