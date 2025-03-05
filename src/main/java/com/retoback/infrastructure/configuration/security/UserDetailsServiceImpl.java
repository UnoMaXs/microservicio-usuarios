package com.retoback.infrastructure.configuration.security;

import com.retoback.infrastructure.output.jpa.entity.UsuarioEntity;
import com.retoback.infrastructure.output.jpa.repository.IUsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

private final IUsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuarioEntity = usuarioRepository.findByCorreo(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con correo: " + username));

        return new User(usuarioEntity.getCorreo(), usuarioEntity.getClave(), Collections.emptyList());
    }

}
