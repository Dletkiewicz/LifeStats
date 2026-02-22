package pl.dletkiewicz.lifestats.domain.model;

import lombok.Getter;

@Getter
public enum Message {

    USER_ALREADY_EXISTS("User with that email already exists"),
    INVALID_CREDENTIALS("Invalid login credentials");

    private final String value;

    Message(String value) {
        this.value = value;
    }
}