package com.retoback.infrastructure.output.jpa.mapper;

import com.retoback.domain.model.Usuario;
import com.retoback.infrastructure.output.jpa.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUsuarioEntityMapper {
    UsuarioEntity toUsuarioEntity(Usuario usuario);

    Usuario toUsuario(UsuarioEntity usuarioEntity);
}
