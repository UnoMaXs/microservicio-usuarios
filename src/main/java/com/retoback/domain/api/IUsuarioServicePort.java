package com.retoback.domain.api;

import com.retoback.domain.model.Usuario;

public interface IUsuarioServicePort {

    void savePropietario(Usuario usuario);

Usuario findById(Long id);
}
