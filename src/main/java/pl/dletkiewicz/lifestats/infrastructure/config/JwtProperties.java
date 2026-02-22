package pl.dletkiewicz.lifestats.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "lifestats.security.jwt")
public record JwtProperties(String secret, long expirationMinutes) {
}
