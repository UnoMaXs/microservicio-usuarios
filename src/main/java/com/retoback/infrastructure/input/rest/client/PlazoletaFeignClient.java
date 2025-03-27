package com.retoback.infrastructure.input.rest.client;

import com.retoback.infrastructure.configuration.feign.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-plazoleta", url = "http://localhost:8080/restauranteApp", configuration = FeignClientConfig.class)
public interface PlazoletaFeignClient {

    @GetMapping("/{id}/restaurante")
    Long obtenerRestaurateId(@PathVariable("id") Long id);

}
