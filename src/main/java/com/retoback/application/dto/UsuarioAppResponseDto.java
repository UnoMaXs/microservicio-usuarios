package com.retoback.application.dto;

import com.retoback.domain.model.RolesPlazoleta;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class UsuarioAppResponseDto {
    private String nombre;
    private String apellido;
    private Long documentoDeIdentidad;
    private String celular;
    private String correo;
    private LocalDate fechaNacimiento;
    private String clave;
    private RolesPlazoleta rol;

    public UsuarioAppResponseDto() {
    }
}
