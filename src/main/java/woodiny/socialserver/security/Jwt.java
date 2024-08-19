package woodiny.socialserver.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Jwt {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private Algorithm algorithm;

    public String generate(Claims claims) {
        algorithm = Algorithm.HMAC256(secret);

        Date now = new Date();
        return JWT.create()
                .withClaim("userId", claims.getUserId())
                .withClaim("email", claims.getEmail().getAddress())
                .withArrayClaim("roles", claims.getRoles())
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + expiration))
                .sign(algorithm);
    }

    public String refresh(String token) throws JWTVerificationException {
        Claims claims = validate(token);
        claims.expire();
        return generate(claims);
    }

    public Claims validate(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(algorithm).build();
        return new Claims(verifier.verify(token));
    }
}
