package pl.dletkiewicz.lifestats.infrastructure.mapper;

import org.mapstruct.Mapper;
import pl.dletkiewicz.lifestats.domain.model.UserExperience;
import pl.dletkiewicz.lifestats.infrastructure.entity.UserExperienceEntity;

@Mapper(componentModel = "spring")
public interface UserExperienceEntityMapper {

    UserExperienceEntity toEntity(UserExperience userExperience);
}
