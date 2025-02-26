package com.retoback.application.handler;

import com.retoback.application.dto.RestauranteAppRequestDto;
import com.retoback.domain.api.IRestauranteServicePort;
import com.retoback.domain.model.Restaurante;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class RestauranteAppHandler implements IRestauranteAppHandler {

    private final IRestauranteServicePort restauranteServicePort;

    @Override
    public void saveRestauranteInRestauranteApp(RestauranteAppRequestDto restauranteAppRequestDto) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNombreRestaurante(restauranteAppRequestDto.getNombreRestaurante());
        restaurante.setNit(restauranteAppRequestDto.getNit());
        restaurante.setDireccion(restauranteAppRequestDto.getDireccion());
        restaurante.setTelefonoRestaurante(restauranteAppRequestDto.getTelefonoRestaurante());
        restaurante.setUrlLogo(restauranteAppRequestDto.getUrlLogo());
        restaurante.setIdUsuario(restauranteAppRequestDto.getIdUsuario());

        restauranteServicePort.saveRestaurante(restaurante);
    }
}
