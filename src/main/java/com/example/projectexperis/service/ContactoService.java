package com.example.projectexperis.service;

import com.example.projectexperis.entity.Contacto;
import com.example.projectexperis.repository.ContactoRepository;
import com.example.projectexperis.repository.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactoService {

    private final ContactoRepository contactoRepository;
    private final SolicitudRepository solicitudRepository;

    public Flux<Contacto> listarContactos() {
        return contactoRepository.findAll();
    }

    public Mono<Contacto> obtenerPorId(Integer id) {
        return contactoRepository.findById(id);
    }

    public Mono<Contacto> crearContacto(Contacto contacto) {
        return solicitudRepository.findById(contacto.getSolicitudId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Solicitud no encontrada")))
                .then(contactoRepository.save(contacto));
    }

    public Mono<Void> eliminarPorId(Integer id) {
        return contactoRepository.deleteById(id);
    }

    public Flux<Contacto> buscarPorSolicitudId(UUID solicitudId) {
        return contactoRepository.findAllBySolicitudId(solicitudId);
    }
}