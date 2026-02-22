package pl.dletkiewicz.lifestats.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.dletkiewicz.lifestats.domain.exception.InvalidCredentialsException;
import pl.dletkiewicz.lifestats.domain.exception.UserAlreadyExistsException;
import pl.dletkiewicz.lifestats.domain.exception.UserNotExistsException;
import pl.dletkiewicz.lifestats.domain.model.LoginRequest;
import pl.dletkiewicz.lifestats.domain.model.RegisterRequest;
import pl.dletkiewicz.lifestats.domain.model.User;
import pl.dletkiewicz.lifestats.domain.port.api.UserApiPort;
import pl.dletkiewicz.lifestats.domain.port.spi.TokenProviderSpiPort;
import pl.dletkiewicz.lifestats.domain.port.spi.UserSpiPort;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserApiPort {

    private final UserSpiPort userSpiPort;
    private final PasswordEncoder passwordEncoder;
    private final TokenProviderSpiPort tokenProviderSpiPort;

    @Override
    public void register(RegisterRequest registerRequest) {
        if (userSpiPort.existsByEmail(registerRequest.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        String passwordHash = passwordEncoder.encode(registerRequest.getPassword());
        User user = new User(UUID.randomUUID(), registerRequest.getEmail(), passwordHash);

        userSpiPort.save(user);
    }

    @Override
    public String login(LoginRequest loginRequest) {
        User user = userSpiPort.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UserNotExistsException(loginRequest.getEmail()));

        boolean isPasswordMatching = passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash());

        if (!isPasswordMatching) {
            throw new InvalidCredentialsException();
        }

        return tokenProviderSpiPort.generateAccessToken(user.getId(), user.getEmail());
    }
}

