# Design

## Loading environment variables

* Created At: April 22, 2025

The system loads the environments variables from a `.env` file, then, read by `application.yml`. Each environment variable has a fallback valua represented in the form `${VARIABLE:default_value}`. For example:

```yml
security:
    jwt:
        secret: ${JWT_SECRET:"secret"}
        expires-in: ${JWT_EXPIRES_IN:3600000}
        issuer: ${JWT_ISSUER:"toshokan.gmorikawa.dev"}
        subject: ${JWT_SUBJECT:"authorization"}
```

The `.env` should contain the following values:

```conf
JWT_SECRET=""               # JWT secret key
JWT_EXPIRES_IN=3600000      # Time in milliseconds of expiration period
JWT_ISSUER="issuer"         # JWT 'iss' claim value
JWT_SUBJECT="subject"       # JWT 'sub' claim value
```

In the code, those variables are injected from the `application.yml` file by using a mechanism from Spring Framework, the `@Value` annotation. For example:

```java
import org.springframework.beans.factory.annotation.Value;

@Value("${security.jwt.secret}")
private String jwtSecret;

@Value("${security.jwt.expires-in}")
private Long jwtExpiresIn;

@Value("${security.jwt.issuer}")
private String jwtIssuer;

@Value("${security.jwt.subject}")
private String jwtSubject;
```

The `@Value` annotation can also read the environments variables directly, meaning that `@Value($JWT_SECRET)` would still work.

So, why not to use the variables from the system instead of `application.yml` variables? Here, the main reason is to maintain coherence. Since other configuration informations are already been loaded through `application.yml`, it makes sense to centralize every configuration there.

Futhermore, the `application.yml` is being provided with default values in case the variable is not provided by the system. It is not ideal to run without proper configuration, but still, it is not a bad configuration either.
