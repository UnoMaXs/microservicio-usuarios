package com.retoback.application.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpleadoRequestDto {

    private String nombreEmpleado;
    private String apellidoEmpleado;
    private Long documentoDeIdentidadEmpleado;
    private String celularEmpleado;
    private String correo;
    private String clave;

}
