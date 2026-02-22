package pl.dletkiewicz.lifestats.infrastructure.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.dletkiewicz.lifestats.domain.port.spi.TokenProviderSpiPort;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider implements TokenProviderSpiPort {

    private final JwtProperties jwtProperties;

    @Override
    public String generateAccessToken(UUID userId, String email) {
        Instant now = Instant.now();
        Instant expiry = now.plus(jwtProperties.expirationMinutes(), ChronoUnit.MINUTES);

        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("email", email)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiry))
                .signWith(Keys.hmacShaKeyFor(jwtProperties.secret().getBytes(StandardCharsets.UTF_8)),
                        SignatureAlgorithm.HS256).compact();
    }
}
