package pl.dletkiewicz.lifestats.application.dto;

import java.util.UUID;

public record RegisterUserResultDto(UUID userId, String nickname) {
}
