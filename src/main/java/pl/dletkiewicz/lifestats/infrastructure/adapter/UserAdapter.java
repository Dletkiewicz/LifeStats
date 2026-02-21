package pl.dletkiewicz.lifestats.infrastructure.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.dletkiewicz.lifestats.domain.model.User;
import pl.dletkiewicz.lifestats.domain.port.spi.UserSpiPort;
import pl.dletkiewicz.lifestats.infrastructure.mapper.UserEntityMapper;
import pl.dletkiewicz.lifestats.infrastructure.repository.UserJpaRepository;

@Component
@RequiredArgsConstructor
public class UserAdapter implements UserSpiPort {

    private final UserJpaRepository userJpaRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public void save(User user) {
        userJpaRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }
}
