package com.retoback.infrastructure.output.jpa.adapter;

import com.retoback.domain.model.Restaurante;
import com.retoback.domain.spi.IRestaurantePersistencePort;
import com.retoback.infrastructure.output.jpa.mapper.IRestauranteEntityMapper;
import com.retoback.infrastructure.output.jpa.repository.IRestauranteRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestauranteJpaAdapter implements IRestaurantePersistencePort {
    private final IRestauranteRepository restauranteRepository;
    private final IRestauranteEntityMapper restauranteEntityMapper;

    @Override
    public void saveRestaurante(Restaurante restaurante) {
restauranteRepository.save(restauranteEntityMapper.toRestauranteEntity(restaurante));
    }
}
