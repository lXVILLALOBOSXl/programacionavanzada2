package com.amigos;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;

public class Pelicula {
    private String nombre;
    private Duration duracion;

    public Pelicula(String nombre, Duration duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
    }

    public Pelicula() { }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Duration getDuracion() {
        return duracion;
    }

    public void setDuracion(Duration duracion) {
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
