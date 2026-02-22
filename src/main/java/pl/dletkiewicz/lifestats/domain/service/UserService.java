package pl.dletkiewicz.lifestats.domain.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.dletkiewicz.lifestats.domain.exception.InvalidCredentialsException;
import pl.dletkiewicz.lifestats.domain.exception.NicknameAlreadyTakenException;
import pl.dletkiewicz.lifestats.domain.exception.UserAlreadyExistsException;
import pl.dletkiewicz.lifestats.domain.exception.UserNotExistsException;
import pl.dletkiewicz.lifestats.domain.model.*;
import pl.dletkiewicz.lifestats.domain.port.api.UserApiPort;
import pl.dletkiewicz.lifestats.domain.port.spi.TokenProviderSpiPort;
import pl.dletkiewicz.lifestats.domain.port.spi.UserExperienceSpiPort;
import pl.dletkiewicz.lifestats.domain.port.spi.UserProfileSpiPort;
import pl.dletkiewicz.lifestats.domain.port.spi.UserSpiPort;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserApiPort {

    private final UserSpiPort userSpiPort;
    private final UserProfileSpiPort userProfileSpiPort;
    private final UserExperienceSpiPort userExperienceSpiPort;
    private final PasswordEncoder passwordEncoder;
    private final TokenProviderSpiPort tokenProviderSpiPort;

    @Override
    @Transactional
    public RegisterUserResult register(RegisterRequest registerRequest) {
        if (userSpiPort.existsByEmail(registerRequest.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        if (userProfileSpiPort.existsByNickname(registerRequest.getNickname())) {
            throw new NicknameAlreadyTakenException();
        }

        String passwordHash = passwordEncoder.encode(registerRequest.getPassword());

        UUID userId = UUID.randomUUID();
        User user = new User(userId, registerRequest.getEmail(), passwordHash);
        UserProfile userProfile = new UserProfile(userId, registerRequest.getNickname());
        UserExperience userExperience = new UserExperience(userId, 0L);

        userSpiPort.save(user);
        userProfileSpiPort.save(userProfile);
        userExperienceSpiPort.save(userExperience);

        return new RegisterUserResult(userId, userProfile.getNickname());
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

