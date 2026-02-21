package pl.dletkiewicz.lifestats.application.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dletkiewicz.lifestats.application.dto.RegisterRequestDto;
import pl.dletkiewicz.lifestats.application.mapper.UserMapper;
import pl.dletkiewicz.lifestats.domain.port.api.UserApiPort;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserApiPort userApiPort;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequestDto payload) {
        userApiPort.register(userMapper.map(payload));
        return ResponseEntity.ok().build();
    }
}
