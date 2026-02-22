package pl.dletkiewicz.lifestats.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dletkiewicz.lifestats.infrastructure.entity.UserProfileEntity;

import java.util.UUID;

@Repository
public interface UserProfileJpaRepository extends JpaRepository<UserProfileEntity, UUID> {

    boolean existsByNickname(String nickname);
}
