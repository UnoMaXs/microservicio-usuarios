package com.retoback.application.mapper;

import com.retoback.application.dto.UsuarioAppRequestDto;
import com.retoback.domain.model.Usuario;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioAppRequestMapper {

    public  Usuario toUsuario(UsuarioAppRequestDto usuarioAppRequestDto) {
        if (usuarioAppRequestDto == null) {
            return null;
        }

        return new Usuario(
                null,
                usuarioAppRequestDto.getNombre(),
                usuarioAppRequestDto.getApellido(),
                usuarioAppRequestDto.getDocumentoDeIdentidad(),
                usuarioAppRequestDto.getCelular(),
                usuarioAppRequestDto.getFechaNacimiento(),
                usuarioAppRequestDto.getCorreo(),
                usuarioAppRequestDto.getClave(),
                usuarioAppRequestDto.getRol()
        );
    }
}
