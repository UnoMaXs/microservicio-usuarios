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
        usuarioServicePort.saveUsuario(usuario);
    }

    @Override
    public String findRolById(Long id) {
        return usuarioServicePort.findRolById(id);
    }


}
