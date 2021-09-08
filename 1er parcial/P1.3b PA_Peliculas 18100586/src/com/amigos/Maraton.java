package com.amigos;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Maraton {
    private LocalTime duracion;
    private int numeroDePeliculas;
    private List<Pelicula> peliculas;

    public Maraton(LocalTime duracion, List<Pelicula> peliculas) {
        this.duracion = duracion;
        this.numeroDePeliculas = peliculas.size();
        this.peliculas = peliculas;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public int getNumeroDePeliculas() { return numeroDePeliculas; }

    public void setNumeroDePeliculas(int numeroDePeliculas) { this.numeroDePeliculas = numeroDePeliculas; }

    public void organizate() {
        System.out.println("Hola");

    }

    private int factorial(int numeroDePeliculas){
        if (numeroDePeliculas <= 1){
            return 1;
        }
        return numeroDePeliculas * factorial(numeroDePeliculas -1);
    }

    @Override
    public String toString() {
        return "Maraton{" +
                "duracion=" + duracion +
                ", numeroDePeliculas=" + numeroDePeliculas +
                ", peliculas=" + peliculas +
                '}';
    }


}
