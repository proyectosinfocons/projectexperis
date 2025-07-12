package com.example.projectexperis.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "solicitudes")
public class Solicitud {


    @Id
    private Integer id;

    private String marca;
    private String tipoSolicitud;
    private LocalDate fechaEnvio;
    private String numeroContacto;
    private String nombreContacto;
}