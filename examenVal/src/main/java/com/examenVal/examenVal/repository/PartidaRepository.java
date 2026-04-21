package com.examenVal.examenVal.repository;

import com.examenVal.examenVal.models.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartidaRepository extends JpaRepository<Partida,Long> {
    Optional<Partida> findByNombre(String nombre);
}
