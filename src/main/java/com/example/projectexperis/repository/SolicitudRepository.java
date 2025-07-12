package com.example.projectexperis.repository;

import com.example.projectexperis.entity.Solicitud;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface SolicitudRepository extends ReactiveCrudRepository<Solicitud, Integer> {}
