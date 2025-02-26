package com.retoback.domain.spi;

import com.retoback.domain.model.Restaurante;

public interface IRestaurantePersistencePort {

     void saveRestaurante(Restaurante restaurante);
}
