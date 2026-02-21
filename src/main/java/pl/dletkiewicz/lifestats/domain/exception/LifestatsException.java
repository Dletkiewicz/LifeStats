package pl.dletkiewicz.lifestats.domain.exception;

import lombok.Getter;

@Getter
public abstract class LifestatsException extends RuntimeException {
    protected LifestatsException(String message) {
        super(message);
    }
}