package pl.dletkiewicz.lifestats.domain.port.spi;

import pl.dletkiewicz.lifestats.domain.model.UserExperience;

public interface UserExperienceSpiPort {

    void save(UserExperience userExperience);
}
