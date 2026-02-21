package pl.dletkiewicz.lifestats.domain.port.spi;

import pl.dletkiewicz.lifestats.domain.model.User;

public interface UserSpiPort {

    void save(User user);

    boolean existsByEmail(String email);
}
