package com.retoback.application.handler;

import com.retoback.application.dto.UsuarioAppRequestDto;
import com.retoback.domain.model.Usuario;

public interface IUsuarioAppHandler {

    void saveUsuarioInUsuarioApp(UsuarioAppRequestDto usuarioAppRequestDto);

    String findRolById(Long id);

}
