package pl.dletkiewicz.lifestats.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserResult {
    private UUID userId;
    private String nickname;
}
