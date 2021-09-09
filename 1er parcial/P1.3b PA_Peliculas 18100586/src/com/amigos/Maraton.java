package com.amigos;

import javax.sound.midi.Soundbank;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.time.Duration;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.util.*;

public class Maraton {
    private Duration duracion;
    private int numeroDePeliculas;
    private List<Pelicula> peliculas;
    private List<Duration> posiblesSumas;
    TreeMap <Duration,List<Pelicula>>contenedor = new TreeMap<Duration,List<Pelicula>>();

    public Maraton(Duration duracion, List<Pelicula> peliculas) {
        this.duracion = duracion;
        this.numeroDePeliculas = peliculas.size();
        this.peliculas = peliculas;
    }

    public Duration getDuracion() {
        return duracion;
    }

    public void setDuracion(Duration duracion) {
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

    public void organizate(){
        this.sum(0, Duration.ZERO, new ArrayList<Pelicula>() );
        Set set = this.contenedor.entrySet();
        Iterator i = set.iterator();
        // Display elements
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue().toString());
        }
    }

    public void sum(int l, Duration count, ArrayList<Pelicula> peliculas) {
        if(l > (this.peliculas.size()-1)){
            ArrayList<Pelicula> posiblesPeliculas = new ArrayList<Pelicula>(peliculas);
            this.contenedor.put(count,posiblesPeliculas);
            return;
        }
        peliculas.add(this.peliculas.get(l));
        sum(l+1, count.plus(this.peliculas.get(l).getDuracion()), new ArrayList<Pelicula>(peliculas));
        peliculas.remove(peliculas.size()-1);
        sum(l+1,count, new ArrayList<Pelicula>(peliculas));
    }

    private void printListMovies(ArrayList<Pelicula> peliculasParaVer){
        System.out.println();
        for (Pelicula pelicula :
                peliculasParaVer) {
            System.out.println(pelicula.toString());
        }
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
