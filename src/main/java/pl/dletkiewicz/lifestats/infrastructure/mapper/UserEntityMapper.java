package pl.dletkiewicz.lifestats.infrastructure.mapper;

import org.mapstruct.Mapper;
import pl.dletkiewicz.lifestats.domain.model.User;
import pl.dletkiewicz.lifestats.infrastructure.entity.UserEntity;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);
}
