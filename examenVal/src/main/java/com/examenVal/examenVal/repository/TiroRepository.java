package com.examenVal.examenVal.repository;

import com.examenVal.examenVal.models.Tiro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TiroRepository extends JpaRepository<Tiro,Long> {
    Optional<Tiro> findByNombre(String nombre);
}
