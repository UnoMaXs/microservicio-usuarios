package com.retoback.application.mapper;


import com.retoback.application.dto.PlatoAppRequestDto;
import com.retoback.domain.model.Plato;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlatoAppResponseMapper {

    PlatoAppRequestDto toPlatoAppRequestDto(Plato plato);
}
