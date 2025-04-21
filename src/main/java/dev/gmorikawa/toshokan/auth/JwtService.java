package dev.gmorikawa.toshokan.auth;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import dev.gmorikawa.toshokan.user.User;
import dev.gmorikawa.toshokan.user.enumerator.UserRole;

@Service
public class JwtService {

    @Autowired
    Environment env;
    
    private final String tokenIssuer = "toshokan_token";
    private final String tokenSubject = "authentication_token";

    public String generateToken(User user) {
        return issue(user.getUsername(), user.getRole());
    }

    public String extractUsername(String token) {
        return extractClaim(token, "username");
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private String issue(String username, UserRole role) {
        Date issuedAt = new Date();
        Date notBefore = new Date(issuedAt.getTime());
        Date expiresAt = new Date(issuedAt.getTime() + env.getProperty("JWT_EXPIRES_IN", Long.class));

        Algorithm algorithm = Algorithm.HMAC256(env.getProperty("JWT_SECRET"));

        String token = JWT.create()
            .withIssuer(tokenIssuer)
            .withSubject(tokenSubject)
            .withClaim("username", username)
            .withClaim("role", role.toString())
            .withIssuedAt(issuedAt)
            .withNotBefore(notBefore)
            .withExpiresAt(expiresAt)
            .sign(algorithm);

        return token;
    }

    private Date extractExpiration(String token) {
        return verify(token).getExpiresAt();
    }

    private String extractClaim(String token, String claim) {
        return verify(token).getClaim(claim).asString();
    }

    private DecodedJWT verify(String token) {
        Algorithm algorithm = Algorithm.HMAC256(env.getProperty("JWT_SECRET"));

        JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer(tokenIssuer)
            .withSubject(tokenSubject)
            .build();
        
        return verifier.verify(token);
    }
}
