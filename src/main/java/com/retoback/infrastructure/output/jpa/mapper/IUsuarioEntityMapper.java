package com.retoback.infrastructure.output.jpa.mapper;

import com.retoback.domain.model.Usuario;
import com.retoback.infrastructure.output.jpa.entity.UsuarioEntity;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IUsuarioEntityMapper {

    public UsuarioEntity toUsuarioEntity(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(usuario.getId());
        entity.setNombre(usuario.getNombre());
        entity.setApellido(usuario.getApellido());
        entity.setDocumentoDeIdentidad(usuario.getDocumentoDeIdentidad());
        entity.setCelular(usuario.getCelular());
        entity.setFechaNacimiento(usuario.getFechaNacimiento());
        entity.setCorreo(usuario.getCorreo());
        entity.setClave(usuario.getClave());
        entity.setRol(usuario.getRol());

        return entity;
    }

    public Usuario toUsuario(UsuarioEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Usuario(
                entity.getId(),
                entity.getNombre(),
                entity.getApellido(),
                entity.getDocumentoDeIdentidad(),
                entity.getCelular(),
                entity.getFechaNacimiento(),
                entity.getCorreo(),
                entity.getClave(),
                entity.getRol()
        );
    }
}
