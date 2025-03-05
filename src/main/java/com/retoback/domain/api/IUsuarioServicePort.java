package com.retoback.domain.api;

import com.retoback.domain.model.Usuario;

public interface IUsuarioServicePort {

    void saveUsuario(Usuario usuario);
    String findRolById(Long id);
    Usuario findUsuarioByCorreo(String correo);
}
