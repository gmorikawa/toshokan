package dev.gmorikawa.toshokan.application.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gmorikawa.toshokan.domain.auth.AuthenticationService;
import dev.gmorikawa.toshokan.domain.auth.entity.Credential;
import dev.gmorikawa.toshokan.domain.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.domain.user.entity.UserSession;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }
    
    @PostMapping("/login")
    public ResponseEntity<UserSession> login(@RequestBody Credential entity) {
        return ResponseEntity.ok(service.login(entity));
    }
    
    @GetMapping("/logged-user")
    public LoggedUser getLoggedUser(
        @RequestAttribute LoggedUser loggedUser
    ) {
        return loggedUser;
    }
}
