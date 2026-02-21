package pl.dletkiewicz.lifestats.domain.exception;

import static pl.dletkiewicz.lifestats.domain.model.Message.USER_ALREADY_EXISTS;

public class UserAlreadyExistsException extends LifestatsException {
    public UserAlreadyExistsException() {
        super(USER_ALREADY_EXISTS.getValue());
    }
}
