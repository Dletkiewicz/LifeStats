package pl.dletkiewicz.lifestats.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserExperience {
    private UUID userId;
    private Long totalExperiencePoints;
}
