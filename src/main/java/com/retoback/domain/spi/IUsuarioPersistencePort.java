package com.retoback.domain.spi;

import com.retoback.domain.model.Usuario;

public interface IUsuarioPersistencePort {

    void saveUsuario(Usuario usuario);
    String findRolById(Long id);
    Usuario findByCorreo(String correo);
}
