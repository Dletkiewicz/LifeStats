package pl.dletkiewicz.lifestats.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dletkiewicz.lifestats.infrastructure.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);
}
