package com.retoback.application.handler;

import com.retoback.application.dto.LoginRequestDto;
import com.retoback.application.dto.LoginResponseDto;
import com.retoback.application.dto.RegisterRequestDto;
import com.retoback.application.mapper.IUsuarioAppRequestMapper;
import com.retoback.domain.api.IUsuarioServicePort;
import com.retoback.domain.model.Usuario;
import com.retoback.infrastructure.exception.BusinessException;
import com.retoback.infrastructure.configuration.security.jwt.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthAppHandler implements IAuthAppHandler {

    private final IUsuarioServicePort usuarioServicePort;
    private final IUsuarioAppRequestMapper usuarioAppRequestMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;




    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Usuario usuario = usuarioServicePort.findUsuarioByCorreo(loginRequestDto.getCorreo());

        // Validar la contraseña
        if (!passwordEncoder.matches(loginRequestDto.getClave(), usuario.getClave())) {
            throw new BusinessException("Contraseña incorrecta");
        }

        // Generar el token JWT
        String jwtToken = jwtService.generate(usuario);

        return new LoginResponseDto(jwtToken);    }

    @Override
    public void register(RegisterRequestDto registerRequestDto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(registerRequestDto.getNombre());
        usuario.setApellido(registerRequestDto.getApellido());
        usuario.setDocumentoDeIdentidad(registerRequestDto.getDocumentoDeIdentidad());
        usuario.setCelular(registerRequestDto.getCelular());
        usuario.setFechaNacimiento(registerRequestDto.getFechaNacimiento());
        usuario.setCorreo(registerRequestDto.getCorreo());
        usuario.setClave(passwordEncoder.encode(registerRequestDto.getClave()));
        usuario.setRol(registerRequestDto.getRol());

        usuarioServicePort.saveUsuario(usuario);
    }
}
