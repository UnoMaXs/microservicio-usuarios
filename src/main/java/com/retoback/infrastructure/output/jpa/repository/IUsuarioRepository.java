package com.retoback.infrastructure.output.jpa.repository;

import com.retoback.infrastructure.output.jpa.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByDocumentoDeIdentidad(Long documentoDeIdentidad);
}
