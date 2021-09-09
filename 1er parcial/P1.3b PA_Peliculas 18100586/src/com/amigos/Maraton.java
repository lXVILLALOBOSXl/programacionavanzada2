package com.amigos;

import java.lang.reflect.Array;
import java.text.Format;
import java.time.Duration;
import java.time.LocalTime;
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
     * Relaciona dicha posible suma con la lista de Peliculas que regresa dicha suma.
     */
    ArrayList<PosiblesPeliculas> posiblesPeliculas = new ArrayList<PosiblesPeliculas>();

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
        //Ordenamos el arreglo por las duraciones de pelicula (De menor a mayor)
        posiblesPeliculas.sort(Comparator.comparing(PosiblesPeliculas::getDuracionPosiblesPeliculas));
        //Se utiliza para iterar desde la mayor duracion en las combinaciones posibles
        int posicion = posiblesPeliculas.size()-1;
        /***
         * Mientras la resta entre el tiempo de maratonn y la mayor suma de las sumas posibles de
         * negativo, quiere decir que la mayor suma posibles es mayor que la duracion del maraton,
         * se descarta como posible combinacion y se elimina del mapa
         * si alguna combinacion es menor que al tiempo mayor quiere decir que es la mejor opcion
         * ya que se ordena de mayor a menor
         */
        while (this.duracion.minus(posiblesPeliculas.get(posicion).getDuracionPosiblesPeliculas().abs()).isNegative()){
            posiblesPeliculas.remove(posicion);
            posicion--;
        }
        /**
         * Una vez que se elimminan las combinaciones que por su duracion no pueden ser sugeridas se verifica si la duracion mayor
         * es igual a la anterior, mientras la resta entre la ultima y la penultima den 0 quieren decir que son iguales, se imprime el mayor
         * y se elimina de la lista por que ya fue sugerido y vuelve a preguntar si hay otro igual
         */
        int numeroDeSugerencia = 1;
        while (posiblesPeliculas.get(posiblesPeliculas.size()-1).getDuracionPosiblesPeliculas().minus(posiblesPeliculas.get(posiblesPeliculas.size()-2).getDuracionPosiblesPeliculas().abs()).isZero()){
            printListMovies(posiblesPeliculas.get(posiblesPeliculas.size()-1).getPosiblesPeliculas(),numeroDeSugerencia);
            posiblesPeliculas.remove(posiblesPeliculas.size()-1);
            numeroDeSugerencia++;
        }
        //Si ya no hay ninguna iguall imprimimos la sugerencia
        printListMovies(posiblesPeliculas.get(posiblesPeliculas.size()-1).getPosiblesPeliculas(),numeroDeSugerencia);
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
            this.posiblesPeliculas.add(new PosiblesPeliculas(count,posiblesPeliculas));
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
    private void printListMovies(List<Pelicula> peliculasParaVer, int sugerencia){
        System.out.println("\nSugerencia " + sugerencia + " : ");
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
