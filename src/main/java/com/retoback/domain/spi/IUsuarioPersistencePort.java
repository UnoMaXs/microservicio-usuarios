package com.retoback.domain.spi;

import com.retoback.domain.model.Usuario;

import java.util.Optional;

public interface IUsuarioPersistencePort {

    void saveUsuario(Usuario usuario);
    String findRolById(Long id);
    Usuario findByCorreo(String correo);
    Optional<Usuario> findById(Long id);



}
