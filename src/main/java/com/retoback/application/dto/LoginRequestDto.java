package com.retoback.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    private String correo;
    private String clave;
}
