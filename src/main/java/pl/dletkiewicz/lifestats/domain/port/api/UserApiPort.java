package pl.dletkiewicz.lifestats.domain.port.api;

import pl.dletkiewicz.lifestats.domain.model.LoginRequest;
import pl.dletkiewicz.lifestats.domain.model.RegisterRequest;
import pl.dletkiewicz.lifestats.domain.model.RegisterUserResult;

public interface UserApiPort {

    RegisterUserResult register(RegisterRequest registerRequest);

    String login(LoginRequest loginRequest);
}
