package com.retoback.application.dto;

import com.retoback.domain.model.RolesPlazoleta;

import java.time.LocalDateTime;

public class UsuarioAppResponseDto {
    private String nombre;
    private String apellido;
    private Long documentoDeIdentidad;
    private String celular;
    private LocalDateTime FechaNacimiento;
    private String correo;
    private String clave;
    private RolesPlazoleta rol;
}
