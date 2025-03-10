package com.retoback.application.handler;

import com.retoback.application.dto.UsuarioAppRequestDto;


public interface IUsuarioAppHandler {

    void saveUsuarioInUsuarioApp(UsuarioAppRequestDto usuarioAppRequestDto);

    String findRolById(Long id);

}
