package dev.gmorikawa.toshokan.domain.auth;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import dev.gmorikawa.toshokan.domain.user.User;
import dev.gmorikawa.toshokan.domain.user.enumerator.UserRole;

@Service
public class JwtService {

    @Value("${security.jwt.secret}")
    private String jwtSecret;

    @Value("${security.jwt.expires-in}")
    private Long jwtExpiresIn;

    @Value("${security.jwt.issuer}")
    private String jwtIssuer;

    @Value("${security.jwt.subject}")
    private String jwtSubject;

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
        Date expiresAt = new Date(issuedAt.getTime() + jwtExpiresIn);

        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

        String token = JWT.create()
            .withIssuer(jwtIssuer)
            .withSubject(jwtSubject)
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
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

        JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer(jwtIssuer)
            .withSubject(jwtSubject)
            .build();
        
        return verifier.verify(token);
    }
}
