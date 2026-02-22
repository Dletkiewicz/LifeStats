package pl.dletkiewicz.lifestats.infrastructure.mapper;

import org.mapstruct.Mapper;
import pl.dletkiewicz.lifestats.domain.model.UserProfile;
import pl.dletkiewicz.lifestats.infrastructure.entity.UserProfileEntity;

@Mapper(componentModel = "spring")
public interface UserProfileEntityMapper {

    UserProfileEntity toEntity(UserProfile userProfile);
}
