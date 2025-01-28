package dev.gmorikawa.toshokan.user;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            User u1 = new User("gmorikawa", "gmorikawa", "gabriel.morikawa@email.com");
            User u2 = new User("rossana", "cantaffa", "rossana.cantaffa@email.com");

            repository.saveAll(List.of(u1, u2));
        };
    }
}
