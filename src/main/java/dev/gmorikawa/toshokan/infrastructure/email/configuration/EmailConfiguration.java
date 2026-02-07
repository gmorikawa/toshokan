package dev.gmorikawa.toshokan.infrastructure.email.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.gmorikawa.toshokan.core.notification.email.contract.EmailClient;
import dev.gmorikawa.toshokan.infrastructure.email.core.JakartaEmailClient;

@Configuration
public class EmailConfiguration {

    @Value("${email.host}")
    private String host;

    @Value("${email.port}")
    private int port;

    @Value("${email.username}")
    private String username;

    @Value("${email.password}")
    private String password;

    @Bean
    protected EmailClient buildEmailClient() {
        return new JakartaEmailClient(host, port, username, password);
    }
}
