package pl.dletkiewicz.lifestats.infrastructure.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.dletkiewicz.lifestats.domain.model.UserProfile;
import pl.dletkiewicz.lifestats.domain.port.spi.UserProfileSpiPort;
import pl.dletkiewicz.lifestats.infrastructure.mapper.UserProfileEntityMapper;
import pl.dletkiewicz.lifestats.infrastructure.repository.UserProfileJpaRepository;

@Component
@RequiredArgsConstructor
public class UserProfileAdapter implements UserProfileSpiPort {

    private final UserProfileEntityMapper userProfileEntityMapper;
    private final UserProfileJpaRepository userProfileJpaRepository;

    @Override
    public void save(UserProfile userProfile) {
        userProfileJpaRepository.save(userProfileEntityMapper.toEntity(userProfile));
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return userProfileJpaRepository.existsByNickname(nickname);
    }
}
