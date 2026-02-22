package pl.dletkiewicz.lifestats.domain.port.api;

import pl.dletkiewicz.lifestats.domain.model.LoginRequest;

public interface AuthApiPort {

    String login(LoginRequest loginRequest);
}
