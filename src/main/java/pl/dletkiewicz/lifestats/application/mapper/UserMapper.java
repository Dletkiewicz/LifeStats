package pl.dletkiewicz.lifestats.application.mapper;

import org.mapstruct.Mapper;
import pl.dletkiewicz.lifestats.application.dto.LoginRequestDto;
import pl.dletkiewicz.lifestats.application.dto.RegisterRequestDto;
import pl.dletkiewicz.lifestats.application.dto.RegisterUserResultDto;
import pl.dletkiewicz.lifestats.domain.model.LoginRequest;
import pl.dletkiewicz.lifestats.domain.model.RegisterRequest;
import pl.dletkiewicz.lifestats.domain.model.RegisterUserResult;

@Mapper(componentModel = "spring")
public interface UserMapper {

    RegisterRequest map(RegisterRequestDto registerRequestDto);

    RegisterUserResultDto toDto(RegisterUserResult registerUserResult);

    LoginRequest map(LoginRequestDto loginRequestDto);
}
