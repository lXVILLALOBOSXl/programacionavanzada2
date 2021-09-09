package com.amigos;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;

/***
 * Almacena y relaciona la duracion de una posible lista de peliculas para sugerir
 */
public class PosiblesPeliculas  {
    private List<Pelicula> posiblesPeliculas;
    private Duration duracionPosiblesPeliculas;

    public PosiblesPeliculas(Duration duracionPosiblesPeliculas, List<Pelicula> posiblesPeliculas ) {
        this.posiblesPeliculas = posiblesPeliculas;
        this.duracionPosiblesPeliculas = duracionPosiblesPeliculas;
    }

    public Duration getDuracionPosiblesPeliculas() {
        return duracionPosiblesPeliculas;
    }

    public List<Pelicula> getPosiblesPeliculas() {
        return posiblesPeliculas;
    }
}
