package com.retoback.application.mapper;

import com.retoback.application.dto.UsuarioAppRequestDto;
import com.retoback.application.dto.UsuarioAppResponseDto;
import com.retoback.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IUsuarioAppResponseMapper {

    UsuarioAppResponseDto toUsuarioAppResponseDto(Usuario usuario){
        if (usuario == null) {
            return null;
        }

        return new UsuarioAppResponseDto(
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getDocumentoDeIdentidad(),
                usuario.getCelular(),
                usuario.getFechaNacimiento(),
                usuario.getCorreo(),
                usuario.getClave(),
                usuario.getRol()
        );
    }

}
