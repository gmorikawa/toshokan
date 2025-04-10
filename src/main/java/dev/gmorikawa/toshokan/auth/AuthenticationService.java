package dev.gmorikawa.toshokan.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.user.User;
import dev.gmorikawa.toshokan.user.UserRepository;

@Service
public class AuthenticationService {

    private final JwtService jwtService;
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(JwtService jwtService, UserRepository repository, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.repository = repository;
        this.authenticationManager = authenticationManager;
    }

    public TokenCredential login(Credential credentials) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword())
        );

        User user = repository.findByUsername(credentials.getUsername()).orElseThrow();

        String token = jwtService.generateToken(user);

        TokenCredential tokenCredential = new TokenCredential();

        tokenCredential.setAccessToken(token);

        return tokenCredential;
    }
}
