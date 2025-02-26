package com.retoback.domain.spi;


import com.retoback.domain.model.Plato;

public interface IPlatoPersistencePort {
    void savePlato(Plato plato);
}
