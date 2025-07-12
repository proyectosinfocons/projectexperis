package com.example.projectexperis.model;

import lombok.Data;

@Data
public class ContactoRequest {
    private String nombreContacto;
    private String numeroContacto;
    private Integer solicitudId; // Usa UUID si tu base de datos lo requiere
}
