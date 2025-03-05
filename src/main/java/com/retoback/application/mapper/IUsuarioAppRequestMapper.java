package com.retoback.application.mapper;

import com.retoback.application.dto.UsuarioAppRequestDto;
import com.retoback.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUsuarioAppRequestMapper {

     Usuario toUsuario(UsuarioAppRequestDto usuarioAppRequestDto);
}
