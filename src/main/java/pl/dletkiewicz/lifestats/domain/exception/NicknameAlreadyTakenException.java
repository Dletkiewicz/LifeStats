package pl.dletkiewicz.lifestats.domain.exception;

import static pl.dletkiewicz.lifestats.domain.model.Message.NICKNAME_TAKEN;

public class NicknameAlreadyTakenException extends RuntimeException {
    public NicknameAlreadyTakenException() {
        super(NICKNAME_TAKEN.getValue());
    }
}
