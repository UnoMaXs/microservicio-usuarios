package com.retoback.application.handler;

import com.retoback.application.dto.UsuarioAppRequestDto;
import com.retoback.application.mapper.UsuarioAppRequestMapper;
import com.retoback.domain.api.IUsuarioServicePort;
import com.retoback.domain.model.Usuario;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioAppHandler implements IUsuarioAppHandler {

    private final IUsuarioServicePort usuarioServicePort;
    private final UsuarioAppRequestMapper usuarioAppRequestMapper;

    @Override
    public void saveUsuarioInUsuarioApp(UsuarioAppRequestDto usuarioAppRequestDto) {
        Usuario usuario = usuarioAppRequestMapper.toUsuario(usuarioAppRequestDto);
        usuarioServicePort.savePropietario(usuario);
    }

}
