package com.retoback.infrastructure.output.jpa.mapper;


import com.retoback.domain.model.Plato;
import com.retoback.infrastructure.output.jpa.entity.PlatoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlatoEntityMapper {
    PlatoEntity toPlatoEntity(Plato plato);

    Plato toPlato(PlatoEntity platoEntity);
}
