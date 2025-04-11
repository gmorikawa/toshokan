package dev.gmorikawa.toshokan.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
    
}
