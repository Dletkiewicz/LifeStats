package pl.dletkiewicz.lifestats.domain.service;

import lombok.experimental.UtilityClass;

import static java.util.Objects.isNull;

@UtilityClass
public class StringUtil {

    public static String normalizeEmail(String email) {
        return isNull(email) ? null : email.trim().toLowerCase();
    }

    public static String normalizeNickname(String nickname) {
        return isNull(nickname) ? null : nickname.trim();
    }
}
