package pl.dletkiewicz.lifestats.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.dletkiewicz.lifestats.domain.exception.UserNotExistsException;
import pl.dletkiewicz.lifestats.domain.model.LoginRequest;
import pl.dletkiewicz.lifestats.domain.model.User;
import pl.dletkiewicz.lifestats.domain.port.api.AuthApiPort;
import pl.dletkiewicz.lifestats.domain.port.spi.UserSpiPort;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthApiPort {

    private final UserSpiPort userSpiPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginRequest loginRequest) {
        User user = userSpiPort.findByEmail(loginRequest.getLogin())
                .orElseThrow(() -> new UserNotExistsException(loginRequest.getLogin()));

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())) {
            //generate token and return
        } else {
            exception -> login or password wrong
        }

    }
}
