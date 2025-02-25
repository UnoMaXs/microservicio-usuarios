package com.retoback.infrastructure.output.jpa.adapter;

import com.retoback.domain.model.Usuario;
import com.retoback.domain.spi.IUsuarioPersistencePort;
import com.retoback.infrastructure.exception.BusinessException;
import com.retoback.infrastructure.output.jpa.mapper.IUsuarioEntityMapper;
import com.retoback.infrastructure.output.jpa.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioJpaAdapter implements IUsuarioPersistencePort {

    private final IUsuarioRepository usuarioRepository;
    private final IUsuarioEntityMapper usuarioEntityMapper;


    @Override
    public void savePropietario(Usuario usuario) {
        if (usuarioRepository.findByDocumentoDeIdentidad(usuario.getDocumentoDeIdentidad()).isPresent()) {
            throw new BusinessException("El usuario con este documento ya existe");
        }
        usuarioRepository.save(usuarioEntityMapper.toUsuarioEntity(usuario));
    }
}

