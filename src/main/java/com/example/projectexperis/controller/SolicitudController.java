package com.example.projectexperis.controller;

import com.example.projectexperis.entity.Solicitud;
import com.example.projectexperis.model.SolicitudRequest;
import com.example.projectexperis.service.SolicitudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class SolicitudController {

    private final SolicitudService service;

    @PostMapping
    public Mono<ResponseEntity<Solicitud>> crear(@RequestBody SolicitudRequest request) {
        return service.registrarSolicitud(request)
                .map(ResponseEntity::ok);
    }

    @GetMapping
    public Flux<Solicitud> listar() {
        return service.listarSolicitudes();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Solicitud>> obtener(@PathVariable int id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/export", produces = "text/csv")
    public Mono<ResponseEntity<String>> exportarCSV() {
        return service.listarSolicitudes()
                .collectList()
                .map(solicitudes -> {
                    StringBuilder csv = new StringBuilder();
                    csv.append('\uFEFF'); // BOM UTF-8
                    csv.append("ID;Marca;Tipo Solicitud;Fecha Envío;Número Contacto;Nombre Contacto\n");
                    for (Solicitud s : solicitudes) {
                        csv.append(s.getId()).append(";")
                                .append(s.getMarca()).append(";")
                                .append(s.getTipoSolicitud()).append(";")
                                .append(s.getFechaEnvio()).append(";")
                                .append(s.getNumeroContacto()).append(";")
                                .append(s.getNombreContacto()).append("\n");
                    }
                    return ResponseEntity.ok()
                            .header("Content-Disposition", "attachment; filename=solicitudes.csv")
                            .header("Content-Type", "text/csv; charset=UTF-8")
                            .body(csv.toString());
                });
    }
}