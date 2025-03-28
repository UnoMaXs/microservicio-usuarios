package com.retoback.domain.usecase;

import com.retoback.domain.api.IUsuarioServicePort;
import com.retoback.domain.model.RolesPlazoleta;
import com.retoback.domain.model.Usuario;
import com.retoback.domain.spi.IUsuarioPersistencePort;
import com.retoback.infrastructure.exception.BusinessException;
import com.retoback.infrastructure.input.rest.client.PlazoletaFeignClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class UsuarioUseCase implements IUsuarioServicePort {
    private static final String REGEX_CORREO = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String REGEX_CELULAR = "^\\+[1-9]{1,3}\\d{10}$";
    private static final String ROLE_ADMINISTRADOR = "ROLE_ADMINISTRADOR";
    private static final String ROLE_PROPIETARIO = "ROLE_PROPIETARIO";

    private final IUsuarioPersistencePort usuarioPersistencePort;
    private final PasswordEncoder passwordEncoder;
    private final PlazoletaFeignClient plazoletaFeignClient;

    public UsuarioUseCase(IUsuarioPersistencePort usuarioPersistencePort, PlazoletaFeignClient plazoletaFeignClient) {
        this.usuarioPersistencePort = usuarioPersistencePort;
        this.plazoletaFeignClient = plazoletaFeignClient;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void saveUsuario(Usuario usuario) {
        if (RolesPlazoleta.CLIENTE.equals(usuario.getRol())) {
            validarYGuardarUsuario(usuario);
            return;
        }

        Authentication auth = obtenerAutenticacion();
        String authority = obtenerAutoridad(auth);

        switch (authority) {
            case ROLE_ADMINISTRADOR:
                validarYGuardarUsuario(usuario);
                break;
            case ROLE_PROPIETARIO:
                if (usuario.getRol() == RolesPlazoleta.EMPLEADO) {
                    Long idPropietario = usuarioPersistencePort.findByCorreo(auth.getName()).getId();
                    crearEmpleadoPorPropietario(usuario, idPropietario);
                } else {
                    throw new BusinessException("No tienes permisos para crear este tipo de usuario.");
                }
                break;
            default:
                throw new BusinessException("No tienes permisos para crear este tipo de usuario.");
        }
    }

    private void validarYGuardarUsuario(Usuario usuario) {
        realizarValidacionesGenerales(usuario);
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
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

    public void crearEmpleadoPorPropietario(Usuario empleado, Long idPropietario) {
        Authentication auth = obtenerAutenticacion();
        validarPropietario(auth, idPropietario);

        empleado.setRol(RolesPlazoleta.EMPLEADO);
        validarCamposEmpleado(empleado);
        if (plazoletaFeignClient.obtenerRestaurateId(empleado.getIdRestaurante()) == null) {
            throw new BusinessException("el Restaurante no existe");
        }
        empleado.setClave(passwordEncoder.encode(empleado.getClave()));
        usuarioPersistencePort.saveUsuario(empleado);
    }

    private Authentication obtenerAutenticacion() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getAuthorities().isEmpty()) {
            throw new BusinessException("No estás autenticado.");
        }
        return auth;
    }

    private String obtenerAutoridad(Authentication auth) {
        return auth.getAuthorities().iterator().next().getAuthority();
    }

    private void validarPropietario(Authentication auth, Long idPropietario) {
        String authority = obtenerAutoridad(auth);
        if (!ROLE_PROPIETARIO.equals(authority)) {
            throw new BusinessException("Solo un propietario puede crear empleados.");
        }

        String correoAutenticado = auth.getName();
        Usuario propietario = usuarioPersistencePort.findByCorreo(correoAutenticado);
        if (propietario == null || !propietario.getId().equals(idPropietario)) {
            throw new BusinessException("El usuario autenticado no coincide con el propietario indicado.");
        }
    }

    private void realizarValidacionesGenerales(Usuario usuario) {
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
    }

    private void validarCamposEmpleado(Usuario usuario) {
        if (usuario.getNombre() == null || usuario.getApellido() == null) {
            throw new BusinessException("Nombre y Apellido obligatorios.");
        }
        if (usuario.getIdRestaurante() == null || usuario.getIdRestaurante() <= 0) {
            throw new BusinessException("Id de restaurante nulo o vacio");
        }
        realizarValidacionesGenerales(usuario);
    }

    private boolean esCorreoValido(String correo) {
        return correo != null && Pattern.matches(REGEX_CORREO, correo);
    }

    private boolean esCelularValido(String celular) {
        return celular != null && Pattern.matches(REGEX_CELULAR, celular);
    }

    private boolean esMayorDeEdad(LocalDate fechaNacimiento) {
        return fechaNacimiento != null &&
                Period.between(fechaNacimiento, LocalDate.now()).getYears() >= 18;
    }
}