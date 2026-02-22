package pl.dletkiewicz.lifestats.application.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.dletkiewicz.lifestats.application.dto.LoginRequestDto;
import pl.dletkiewicz.lifestats.application.dto.LoginResponseDto;
import pl.dletkiewicz.lifestats.application.dto.RegisterRequestDto;
import pl.dletkiewicz.lifestats.application.dto.RegisterUserResultDto;
import pl.dletkiewicz.lifestats.application.mapper.UserMapper;
import pl.dletkiewicz.lifestats.domain.port.api.UserApiPort;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserApiPort userApiPort;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResultDto> register(@RequestBody RegisterRequestDto payload) {
        RegisterUserResultDto responseDto = userMapper.toDto(userApiPort.register(userMapper.map(payload)));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto payload) {
        String token = userApiPort.login(userMapper.map(payload));
        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}
