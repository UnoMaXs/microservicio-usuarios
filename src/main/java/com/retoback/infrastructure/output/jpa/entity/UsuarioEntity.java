package com.retoback.infrastructure.output.jpa.entity;

import com.retoback.domain.model.RolesPlazoleta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private Long documentoDeIdentidad;
    private String celular;
    private LocalDateTime FechaNacimiento;
    private String correo;
    private String clave;
    private RolesPlazoleta rol;

}
