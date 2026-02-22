package pl.dletkiewicz.lifestats.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users_experience")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExperienceEntity {
    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "total_experience_points")
    private Long totalExperiencePoints;

    @Column(name = "version")
    @Version
    private int version;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    void onCreate() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
