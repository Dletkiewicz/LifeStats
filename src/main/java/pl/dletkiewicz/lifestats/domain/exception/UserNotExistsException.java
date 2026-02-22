package pl.dletkiewicz.lifestats.domain.exception;

public class UserNotExistsException extends LifestatsException {
    public UserNotExistsException(String message) {
        super("User with login " + message + " does not exists!");
    }
}
