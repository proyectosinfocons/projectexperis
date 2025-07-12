package com.example.projectexperis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contactos")
public class Contacto {


    @Id
    private Integer id;
    private String nombreContacto;
    private String numeroContacto;
    private int  solicitudId;

}
