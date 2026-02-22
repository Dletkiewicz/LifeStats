package pl.dletkiewicz.lifestats.domain.exception;

import static pl.dletkiewicz.lifestats.domain.model.Message.INVALID_CREDENTIALS;

public class InvalidCredentialsException extends LifestatsException {
    public InvalidCredentialsException() {
        super(INVALID_CREDENTIALS.getValue());
    }
}
