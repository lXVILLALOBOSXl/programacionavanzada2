package com.amigos;

import java.sql.Time;
import java.time.LocalTime;

public class Pelicula {
    private String nombre;
    private LocalTime duracion;

    public Pelicula() {

    }

    public Pelicula(String nombre, LocalTime duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "nombre='" + nombre + '\'' +
                ", duracion=" + duracion +
                '}';
    }
}
