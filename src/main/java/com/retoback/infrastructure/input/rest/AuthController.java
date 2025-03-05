package com.retoback.infrastructure.input.rest;

import com.retoback.application.dto.LoginRequestDto;
import com.retoback.application.dto.LoginResponseDto;
import com.retoback.application.dto.RegisterRequestDto;
import com.retoback.application.dto.TokenResponse;
import com.retoback.application.handler.IAuthAppHandler;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthAppHandler authAppHandler;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        return ResponseEntity.ok(authAppHandler.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto registerRequestDto) {
    authAppHandler.register(registerRequestDto);
    return ResponseEntity.ok("Usuario registrado correctamente");
    }
}
