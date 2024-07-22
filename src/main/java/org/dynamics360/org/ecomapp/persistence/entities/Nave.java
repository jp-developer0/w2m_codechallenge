package org.dynamics360.org.ecomapp.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Nave is an entity that represents a Nave in the e-commerce system.
 * It contains information about the Nave, such as its ID, nombre, pelicula, price, and quantity.
 *
 * @author juanpablogonzalez
 */
 @Entity
public class Nave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String pelicula;

    public Nave() {
    }

    public Nave(Long id, String nombre, String pelicula) {
        this.id = id;
        this.nombre = nombre;
        this.pelicula = pelicula;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }
}
