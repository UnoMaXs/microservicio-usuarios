package com.retoback.infrastructure.output.jpa.adapter;


import com.retoback.domain.model.Plato;
import com.retoback.domain.spi.IPlatoPersistencePort;
import com.retoback.infrastructure.output.jpa.mapper.IPlatoEntityMapper;
import com.retoback.infrastructure.output.jpa.repository.IPlatoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlatoJpaAdapter implements IPlatoPersistencePort {
    private final IPlatoRepository platoRepository;
    private final IPlatoEntityMapper platoEntityMapper;

    @Override
    public void savePlato(Plato plato) {
        platoRepository.save(platoEntityMapper.toPlatoEntity(plato));
    }
}
