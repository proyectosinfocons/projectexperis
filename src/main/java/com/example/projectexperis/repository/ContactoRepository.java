package com.example.projectexperis.repository;

import com.example.projectexperis.entity.Contacto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface ContactoRepository extends ReactiveCrudRepository<Contacto, Integer> {
    Flux<Contacto> findAllBySolicitudId(UUID  solicitudId);
}