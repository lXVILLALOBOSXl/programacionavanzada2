package com.amigos;

import java.text.Format;
import java.time.Duration;
import java.util.*;

import static java.lang.String.format;

/***
 * Representa un Maraton que tiene, un periodo de tiempo (duracion), un numero de peliculas (numeroDePeliculas)
 * y una llista de peliculas(peliculas)
 */
public class Maraton {
    private Duration duracion;
    private int numeroDePeliculas;
    private List<Pelicula> peliculas;
    /***
     * Se utiliza para almacenar las posibles sumas de periodos de tiempo de la lista de peliculas
     * Relaciona dicha posible suma con la lista de Peliculas que regresa dicha suma, se indica que se ordena
     * de mayor a menor rango de tiempo
     */
    TreeMap <Duration,List<Pelicula>>contenedor = new TreeMap<>(Collections.reverseOrder());

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
        /***
         * Mientras la resta entre el tiempo de maratonn y la mayor suma de las sumas posibles de
         * negativo, quiere decir que la mayor suma posibles es mayor que la duracion del maraton,
         * se descarta como posible combinacion y se elimina del mapa
         * si alguna combinacion es menor que al tiempo mayor quiere decir que es la mejor opcion
         * ya que se ordena de mayor a menor
         */
        while (this.duracion.minus(contenedor.firstKey().abs()).isNegative()){
            contenedor.remove(contenedor.firstKey());
        }
        printListMovies((ArrayList<Pelicula>) contenedor.get(contenedor.firstKey()));
    }

    /***
     * Se encarga de realizar todas las posibles sumas y agregar cada posible suma al mapa y lo relaciona
     * con la lista de peliculas que regresa dicha suma de tiempo
     * @param l iteracion dentro de la recursividad
     * @param count suma de tiempos de las peliculas
     * @param peliculas lista de peliculas que se asocia con la suma de tiempo
     */
    public void sum(int l, Duration count, ArrayList<Pelicula> peliculas) {
        /***
         * Si la iteracion es del numero de peliculas
         * Se almacenan la lista de peliculas que se asocia con la suma
         * que se realiza en la recursvidad
         * y regresa al punto donde se quedo
         */
        if(l > (this.peliculas.size()-1)){
            ArrayList<Pelicula> posiblesPeliculas = new ArrayList<Pelicula>(peliculas);
            this.contenedor.put(count,posiblesPeliculas);
            return;
        }
        /***
         * Se agrega la pelicula en el index de la iteracion actual
         */
        peliculas.add(this.peliculas.get(l));
        /***
         * Se suma uno a la iteracion y se hace la suma de la cuenta que se lleva mas el tiempo de la pelicula en la iteracion
         */
        sum(l+1, count.plus(this.peliculas.get(l).getDuracion()), new ArrayList<Pelicula>(peliculas));
        /***
         * Se tiene que eliminar la utlima pelicula que se agrego
         * ya que se retoma la lista de peliculas de la cuenta anterior
         */
        peliculas.remove(peliculas.size()-1);
        /***
         * Se llama el metodo con la cuenta anterior y una iteracion mas
         * para agregar la lista de pelicula y suma y continuar con las demas sumas
         */
        sum(l+1,count, new ArrayList<Pelicula>(peliculas));
    }

    /**
     * Imprime la lista de peliculas de salda
     * @param peliculasParaVer lista peliculas que han sido elegidas como la mejor opcion para ver en el maraton
     */
    private void printListMovies(ArrayList<Pelicula> peliculasParaVer){
        System.out.println();
        for (Pelicula pelicula :
                peliculasParaVer) {
            System.out.println(pelicula.getNombre() + " " + formatDuration(pelicula.getDuracion()));
        }
    }

    /***
     * Convierte Duration a String en HH:mm
     * @param duration El tiempo que dura una pelicula en la lista de las peliculas para ver
     * @return String en formato HH:mm
     */
    private static String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        String positive = String.format(
                "%d:%02d",
                absSeconds / 3600,
                (absSeconds % 3600) / 60,
                absSeconds % 60);
        return seconds < 0 ? "-" + positive : positive;
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
