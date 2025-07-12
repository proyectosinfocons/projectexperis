package com.example.projectexperis.controller;

import com.example.projectexperis.entity.Contacto;
import com.example.projectexperis.model.ContactoRequest;
import com.example.projectexperis.service.ContactoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/contactos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ContactoController {

    private final ContactoService contactoService;

    @GetMapping
    public Flux<Contacto> listar() {
        return contactoService.listarContactos();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Contacto>> obtenerPorId(@PathVariable Integer id) {
        return contactoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<?>> crear(@RequestBody ContactoRequest request) {
        Contacto contacto = new Contacto(
                null,
                request.getNombreContacto(),
                request.getNumeroContacto(),
                request.getSolicitudId()
        );

        return contactoService.crearContacto(contacto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminar(@PathVariable Integer id) {
        return contactoService.eliminarPorId(id)
                .thenReturn(ResponseEntity.noContent().<Void>build());
    }
}