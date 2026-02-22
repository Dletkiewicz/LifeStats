package pl.dletkiewicz.lifestats.application.mapper;

import org.mapstruct.Mapper;
import pl.dletkiewicz.lifestats.application.dto.LoginRequestDto;
import pl.dletkiewicz.lifestats.application.dto.RegisterRequestDto;
import pl.dletkiewicz.lifestats.domain.model.LoginRequest;
import pl.dletkiewicz.lifestats.domain.model.RegisterRequest;

@Mapper(componentModel = "spring")
public interface UserMapper {

    RegisterRequest map(RegisterRequestDto registerRequestDto);

    LoginRequest map(LoginRequestDto loginRequestDto);
}
