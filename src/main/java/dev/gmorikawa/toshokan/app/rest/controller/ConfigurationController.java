package dev.gmorikawa.toshokan.app.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gmorikawa.toshokan.config.ConfigurationService;
import dev.gmorikawa.toshokan.domain.user.User;

@RestController("api.configuration")
@RequestMapping(path = "api/configuration")
public class ConfigurationController {
    
    private final ConfigurationService configurationService;

    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
    
    @PostMapping("/admin")
    public User configureAdmin(
        @RequestBody User user
    ) {
        return configurationService.createFirstAdmin(user);
    }

}
