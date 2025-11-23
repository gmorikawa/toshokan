package dev.gmorikawa.toshokan.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gmorikawa.toshokan.domain.user.User;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }
    
    @PostMapping("/login")
    public ResponseEntity<TokenCredential> login(@RequestBody Credential entity) {
        return ResponseEntity.ok(service.login(entity));
    }
    
    @GetMapping("/logged-user")
    public User getLoggedUser(
        @RequestAttribute User user
    ) {
        return user;
    }
}
