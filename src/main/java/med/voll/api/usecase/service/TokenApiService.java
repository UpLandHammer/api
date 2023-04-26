package med.voll.api.usecase.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.voll.api.exceptions.TokenApiException;
import med.voll.api.gateway.mysql.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenApiService {

    public static final String IDENTIFIER_API_VOLL_MED = "API Voll.med";
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(IDENTIFIER_API_VOLL_MED)
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generateExpireDate())
                    .sign(algorithm);
        } catch (JWTVerificationException exception) {
            throw new TokenApiException("Invalid token", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {

        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer(IDENTIFIER_API_VOLL_MED)
                .build()
                .verify(tokenJWT)
                .getSubject();
        } catch (JWTVerificationException exception) {
            throw new TokenApiException("Invalid token", exception);
        }
    }


    private Instant generateExpireDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
