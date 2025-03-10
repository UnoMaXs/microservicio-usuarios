package com.retoback.application.handler;

import com.retoback.application.dto.UsuarioAppRequestDto;
import com.retoback.application.mapper.IUsuarioAppRequestMapper;
import com.retoback.domain.api.IUsuarioServicePort;
import com.retoback.domain.model.RolesPlazoleta;
import com.retoback.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UsuarioAppHandler implements IUsuarioAppHandler {

    private final IUsuarioServicePort usuarioServicePort;
    private final IUsuarioAppRequestMapper usuarioAppRequestMapper;


    @Override
    public void saveUsuarioInUsuarioApp(UsuarioAppRequestDto usuarioAppRequestDto) {
        Usuario usuario = usuarioAppRequestMapper.toUsuario(usuarioAppRequestDto);
     /*   if (usuario.getRol() == RolesPlazoleta.EMPLEADO) {
            usuario.setNombre(usuarioAppRequestDto.getNombre());
            usuario.setApellido(usuarioAppRequestDto.getApellido());
            usuario.setDocumentoDeIdentidad(usuarioAppRequestDto.getDocumentoDeIdentidad());
            usuario.setCelular(usuarioAppRequestDto.getCelular());
        } */
        usuarioServicePort.saveUsuario(usuario);
    }

    @Override
    public String findRolById(Long id) {
        return usuarioServicePort.findRolById(id);
    }


}
