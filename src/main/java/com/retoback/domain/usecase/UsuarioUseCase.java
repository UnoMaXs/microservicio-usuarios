package com.retoback.domain.usecase;

import com.retoback.domain.api.IUsuarioServicePort;
import com.retoback.domain.model.Usuario;
import com.retoback.domain.spi.IUsuarioPersistencePort;
import com.retoback.infrastructure.exception.BusinessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.Period;

public class UsuarioUseCase implements IUsuarioServicePort {

    private final IUsuarioPersistencePort usuarioPersistencePort;

    public UsuarioUseCase(IUsuarioPersistencePort usuarioPersistencePort) {
        this.usuarioPersistencePort = usuarioPersistencePort;
    }

    @Override
    public void saveUsuario(Usuario usuario) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || auth.getAuthorities().isEmpty()) {
            throw new BusinessException("No estás autenticado, no se puede crear un usuario.");
        }

        String authority = auth.getAuthorities().iterator().next().getAuthority();

        if (!"ROLE_ADMINISTRADOR".equals(authority)) {
            throw new BusinessException("Solo un administrador puede crear usuarios");
        }

        if ("EMPLEADO".equals(usuario.getRol().toString())) {
            if (!"ROLE_PROPIETARIO".equals(authority)) {
                throw new BusinessException("Solo un propietario puede crear empleados.");
            }
        }

        if (!esCorreoValido(usuario.getCorreo())) {
            throw new BusinessException("Correo no válido, revise la estructura (ej. usuario@dominio.com)");
        }

        if (!esCelularValido(usuario.getCelular())) {
            throw new BusinessException("Teléfono inválido; máximo 13 dígitos y debe iniciar con '+'.");
        }

        if (usuario.getDocumentoDeIdentidad() == null || usuario.getDocumentoDeIdentidad() <= 0) {
            throw new BusinessException("Documento de identidad debe ser un número positivo.");
        }


        if (!esMayorDeEdad(usuario.getFechaNacimiento())) {
            throw new BusinessException("El usuario debe ser mayor de 18 años.");
        }

        usuarioPersistencePort.saveUsuario(usuario);
    }

    @Override
    public String findRolById(Long id) {
        return usuarioPersistencePort.findRolById(id);
    }

    @Override
    public Usuario findUsuarioByCorreo(String correo) {
        return usuarioPersistencePort.findByCorreo(correo);
    }


    private boolean esCorreoValido(String correo) {
        if (correo == null) return false;
        return correo.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

    private boolean esCelularValido(String celular) {
        if (celular == null) return false;

        return celular.matches("^\\+[1-9]{1,3}\\d{10}$");
    }

    private boolean esMayorDeEdad(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) return false;

        return Period.between(fechaNacimiento, LocalDate.now()).getYears() >= 18;
    }
 }

