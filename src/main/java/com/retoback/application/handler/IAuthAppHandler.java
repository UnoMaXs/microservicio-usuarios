package com.retoback.application.handler;

import com.retoback.application.dto.LoginRequestDto;
import com.retoback.application.dto.LoginResponseDto;
import com.retoback.application.dto.RegisterRequestDto;

public interface IAuthAppHandler {

    LoginResponseDto login(LoginRequestDto loginRequestDto);

    void register(RegisterRequestDto registerRequestDto);
}
