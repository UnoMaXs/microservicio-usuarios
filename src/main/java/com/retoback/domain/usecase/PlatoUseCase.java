package com.retoback.domain.usecase;


import com.retoback.domain.api.IPlatoServicePort;
import com.retoback.domain.model.Plato;
import com.retoback.domain.spi.IPlatoPersistencePort;

public class PlatoUseCase implements IPlatoServicePort {

    private final IPlatoPersistencePort platoPersistencePort;

    public PlatoUseCase(IPlatoPersistencePort platoPersistencePort) {
        this.platoPersistencePort = platoPersistencePort;
    }


    @Override
    public void savePlato(Plato plato) {

        platoPersistencePort.savePlato(plato);
    }
}
