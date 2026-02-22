package pl.dletkiewicz.lifestats.domain.port.spi;

import java.util.UUID;

public interface TokenProviderSpiPort {
    String generateAccessToken(UUID userId, String email);
}
