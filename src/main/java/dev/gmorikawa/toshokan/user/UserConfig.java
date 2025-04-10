package dev.gmorikawa.toshokan.user;

import java.util.List;

import dev.gmorikawa.toshokan.user.enumerator.UserRole;

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
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            User u1 = new User("gmorikawa", "gmorikawa", "gabriel.morikawa@email.com", UserRole.ADMIN, "Gabriel Morikawa");
            User u2 = new User("arthurm", "reddead", "arthur.morgan@email.com", UserRole.LIBRARIAN, "Arthur Morgan");

            u1.setPassword(passwordEncoder.encode(u1.getPassword()));
            u2.setPassword(passwordEncoder.encode(u2.getPassword()));

            repository.saveAll(List.of(u1, u2));
        };
    }
}
