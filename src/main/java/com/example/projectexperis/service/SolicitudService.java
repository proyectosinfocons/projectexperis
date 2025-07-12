package com.example.projectexperis.service;

import com.example.projectexperis.entity.Contacto;
import com.example.projectexperis.entity.Solicitud;
import com.example.projectexperis.model.SolicitudRequest;
import com.example.projectexperis.repository.ContactoRepository;
import com.example.projectexperis.repository.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SolicitudService {

    private final SolicitudRepository solicitudRepo;
    private final ContactoRepository contactoRepo;

    public Mono<Solicitud> registrarSolicitud(SolicitudRequest request) {
        UUID solicitudId = UUID.randomUUID();

        Solicitud solicitud = new Solicitud(
                null,
                request.getMarca(),
                request.getTipoSolicitud(),
                request.getFechaEnvio(),
                request.getNumeroContacto(),
                request.getNombreContacto()
        );

        return solicitudRepo.save(solicitud)
                .flatMap(savedSolicitud -> {
                    List<Contacto> contactos = request.getContactos().stream()
                            .map(c -> new Contacto(
                                    null,
                                    c.getNombreContacto(),
                                    c.getNumeroContacto(),
                                    savedSolicitud.getId()
                            )).toList();

                    return contactoRepo.saveAll(contactos).then(Mono.just(savedSolicitud));
                });
    }

    public Flux<Solicitud> listarSolicitudes() {
        return solicitudRepo.findAll();
    }

    public Mono<Solicitud> obtenerPorId(int id) {
        return solicitudRepo.findById(id);
    }
}