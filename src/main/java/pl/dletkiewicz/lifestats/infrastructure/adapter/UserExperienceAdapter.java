package pl.dletkiewicz.lifestats.infrastructure.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dletkiewicz.lifestats.domain.model.UserExperience;
import pl.dletkiewicz.lifestats.domain.port.spi.UserExperienceSpiPort;
import pl.dletkiewicz.lifestats.infrastructure.mapper.UserExperienceEntityMapper;
import pl.dletkiewicz.lifestats.infrastructure.repository.UserExperienceJpaRepository;

@Service
@RequiredArgsConstructor
public class UserExperienceAdapter implements UserExperienceSpiPort {

    private final UserExperienceJpaRepository userExperienceJpaRepository;
    private final UserExperienceEntityMapper experienceEntityMapper;

    @Override
    public void save(UserExperience userExperience) {
        userExperienceJpaRepository.save(experienceEntityMapper.toEntity(userExperience));
    }
}
