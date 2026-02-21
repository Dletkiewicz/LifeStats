package pl.dletkiewicz.lifestats.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dletkiewicz.lifestats.domain.exception.UserAlreadyExistsException;
import pl.dletkiewicz.lifestats.domain.model.RegisterRequest;
import pl.dletkiewicz.lifestats.domain.model.User;
import pl.dletkiewicz.lifestats.domain.port.api.UserApiPort;
import pl.dletkiewicz.lifestats.domain.port.spi.UserSpiPort;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService implements UserApiPort {

    private final UserSpiPort userSpiPort;

    @Override
    public void register(RegisterRequest registerRequest) {
        if (userSpiPort.existsByEmail(registerRequest.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        User user = new User(UUID.randomUUID(), registerRequest.getEmail(), registerRequest.getPassword());
        userSpiPort.save(user);
    }
}
