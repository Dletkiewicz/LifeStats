package pl.dletkiewicz.lifestats.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dletkiewicz.lifestats.infrastructure.entity.UserExperienceEntity;

import java.util.UUID;

@Repository
public interface UserExperienceJpaRepository extends JpaRepository<UserExperienceEntity, UUID> {
}
