package org.dynamics360.org.ecomapp.dtos;

import java.io.Serializable;

public record NaveDto (
        Long id,
        String nombre,
        String pelicula
) implements Serializable {
     public String getNombre() {
        return nombre;
    }   
}
