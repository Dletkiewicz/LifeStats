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

import java.util.UUID;

import static pl.dletkiewicz.lifestats.domain.service.StringUtil.normalizeEmail;
import static pl.dletkiewicz.lifestats.domain.service.StringUtil.normalizeNickname;

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
        String email = normalizeEmail(registerRequest.getEmail());
        String nickname = normalizeNickname(registerRequest.getNickname());

        ensureEmailAvailable(email);
        ensureNicknameAvailable(nickname);

        UserRegistrationData userRegistrationData = createRegistrationData(email, nickname, registerRequest.getPassword());
        persistRegistrationData(userRegistrationData);

        return new RegisterUserResult(userRegistrationData.user().getId(), userRegistrationData.userProfile().getNickname());
    }

    @Override
    public String login(LoginRequest loginRequest) {
        String email = normalizeEmail(loginRequest.getEmail());
        User user = findUserByEmailOrThrow(email);

        verifyPasswordOrThrow(loginRequest.getPassword(), user.getPasswordHash());

        return tokenProviderSpiPort.generateAccessToken(user.getId(), user.getEmail());
    }

    private void verifyPasswordOrThrow(String rawPassword, String passwordHash) {
        boolean isPasswordMatching = passwordEncoder.matches(rawPassword, passwordHash);
        if (!isPasswordMatching) {
            throw new InvalidCredentialsException();
        }
    }

    private User findUserByEmailOrThrow(String email) {
        return userSpiPort.findByEmail(email)
                .orElseThrow(() -> new UserNotExistsException(email));
    }

    private void persistRegistrationData(UserRegistrationData userRegistrationData) {
        userSpiPort.save(userRegistrationData.user());
        userProfileSpiPort.save(userRegistrationData.userProfile());
        userExperienceSpiPort.save(userRegistrationData.userExperience());
    }

    private UserRegistrationData createRegistrationData(String email, String nickname, String rawPassword) {
        String passwordHash = passwordEncoder.encode(rawPassword);
        UUID userId = UUID.randomUUID();
        User user = new User(userId, email, passwordHash);
        UserProfile userProfile = new UserProfile(userId, nickname);
        UserExperience userExperience = new UserExperience(userId, 0L);

        return new UserRegistrationData(user, userProfile, userExperience);
    }

    private void ensureEmailAvailable(String email) {
        if (userSpiPort.existsByEmail(email)) {
            throw new UserAlreadyExistsException();
        }
    }

    private void ensureNicknameAvailable(String nickname) {
        if (userProfileSpiPort.existsByNickname(nickname)) {
            throw new NicknameAlreadyTakenException();
        }
    }
}

