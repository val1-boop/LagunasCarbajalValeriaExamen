package com.examenVal.examenVal.repository;

import com.examenVal.examenVal.models.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JugadorRepository extends JpaRepository<Jugador,Long> {
    Optional<Jugador> findByNombre(String nombre);
}
