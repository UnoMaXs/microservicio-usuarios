package com.retoback.domain.spi;

import com.retoback.domain.model.Usuario;

public interface IUsuarioPersistencePort {

    void savePropietario(Usuario usuario);
    Usuario findById(Long id);
}
