package dev.gmorikawa.toshokan.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class ServerPortConfiguration implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Value("${server.port}")
    private Integer port;
 
    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(port);
    }
}
