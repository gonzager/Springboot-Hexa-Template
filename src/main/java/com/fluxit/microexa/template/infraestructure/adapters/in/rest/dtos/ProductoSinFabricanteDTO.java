package com.fluxit.microexa.template.infraestructure.adapters.in.rest.dtos;

import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Data
public class ProductoSinFabricanteDTO {
    String nombre;
    UUID codigo;
    Date fecha_lanzamiento;
    Integer antiguedad;
}
