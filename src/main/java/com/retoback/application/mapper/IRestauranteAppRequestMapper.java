package com.retoback.application.mapper;

import com.retoback.application.dto.RestauranteAppRequestDto;
import com.retoback.domain.model.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestauranteAppRequestMapper {
    Restaurante toRestaurante(RestauranteAppRequestDto restauranteAppRequestDto);
}
