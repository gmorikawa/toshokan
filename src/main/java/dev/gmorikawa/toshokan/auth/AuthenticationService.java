package dev.gmorikawa.toshokan.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.auth.exception.InvalidCredentialsException;
import dev.gmorikawa.toshokan.domain.user.LoggedUser;
import dev.gmorikawa.toshokan.domain.user.User;
import dev.gmorikawa.toshokan.domain.user.UserRepository;
import dev.gmorikawa.toshokan.domain.user.UserSession;

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

    public UserSession login(Credential credentials) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword())
        );

        User user = repository
            .findByUsername(credentials.getUsername())
            .orElseThrow(() -> new InvalidCredentialsException());

        LoggedUser loggedUser = new LoggedUser(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getRole(),
            user.getStatus(),
            user.getFullname()
        );

        String token = jwtService.generateToken(user);

        return new UserSession(loggedUser, token);
    }
}
