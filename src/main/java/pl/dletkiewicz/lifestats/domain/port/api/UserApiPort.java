package pl.dletkiewicz.lifestats.domain.port.api;

import pl.dletkiewicz.lifestats.domain.model.LoginRequest;
import pl.dletkiewicz.lifestats.domain.model.RegisterRequest;

public interface UserApiPort {

    void register(RegisterRequest registerRequest);

    String login(LoginRequest loginRequest);
}
