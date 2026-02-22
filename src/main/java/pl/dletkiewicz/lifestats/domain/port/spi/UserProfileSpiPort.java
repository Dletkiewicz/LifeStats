package pl.dletkiewicz.lifestats.domain.port.spi;

import pl.dletkiewicz.lifestats.domain.model.UserProfile;

public interface UserProfileSpiPort {

    void save(UserProfile userProfile);

    boolean existsByNickname(String nickname);
}
