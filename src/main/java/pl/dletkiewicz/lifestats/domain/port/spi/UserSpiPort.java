package pl.dletkiewicz.lifestats.domain.port.spi;

import pl.dletkiewicz.lifestats.domain.model.User;

import java.util.Optional;

public interface UserSpiPort {

    void save(User user);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
