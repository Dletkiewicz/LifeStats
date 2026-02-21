package pl.dletkiewicz.lifestats.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.dletkiewicz.lifestats.domain.model.User;
import pl.dletkiewicz.lifestats.infrastructure.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    @Mapping(target = "passwordHash", source = "password")
    UserEntity toEntity(User user);
}
