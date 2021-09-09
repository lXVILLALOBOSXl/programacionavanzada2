package com.main;

import com.amigos.Maraton;
import com.amigos.Pelicula;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Maraton maraton = new Maraton(getDuracionMaraton(),getListaDePeliculas(getNumeroDePeliculas()));
        maraton.organizate();
    }

    /***
     * Pide al usuario el peridodo de tiempo
     * @return El periodo de tiempo (Duration) del maraton.
     */
    public static Duration getDuracionMaraton() {
        Scanner scanner = new Scanner(System.in);
        //Almacena el periodo de tiempo y se inicia en 0 para sacar a diferencia entre el tiempo
        //que se ingreso
        Duration duracion = Duration.ZERO;
        //Indica si hay un error al momento de ingresar el dato
        boolean error = false;

        do {
            try {
                scanner = new Scanner(System.in);
                System.out.print("Ingrese el TIEMPO (HH:mm) del Maraton: ");
                //Almacena la diferencia de tiempo entre (0 y el tiempo ingresado con un formato HH:mm
                duracion = Duration.between(LocalTime.MIN, LocalTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("H:mm")));
                //No hay error
                error = false;
            } catch (Exception ex) { //Se se produce una excepcion
                System.out.println("ingrese un formato correcto: HH:mm");
                //Indica que existio un error
                error = true;
            }
        }while (error); //Se vuelven a solicitar los datos


        return duracion;
    }

    /***
     * Pide al usuario el nombre y duracion de la pelicula, lo almacena en una lista de objetos tipo Pelicula
     * @param numeroDePeliculas
     * @return Lista de objetos Pelicula ingresada por el usuario
     */
    public static List<Pelicula> getListaDePeliculas(int numeroDePeliculas) {
        //Almacena objetos de Pelicula
        List<Pelicula> peliculas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        //LLena la lista de peliculas de acuero al numero de peliculas que planea ver el usuario
        for (int i = 0; i < numeroDePeliculas; i++) {
            //Cada numero de pelicula en la lista se tiene que crear desde 0
            Pelicula pelicula = new Pelicula();
            try {
                System.out.print("\nIngrese el TIEMPO (HH:mm) de la pelicula " + (i + 1) + " : ");
                pelicula.setDuracion(Duration.between(LocalTime.MIN, LocalTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("H:mm"))));
                System.out.print("Ingrese el NOMBRE de la pelicula " + (i + 1) + " : ");
                pelicula.setNombre(scanner.nextLine());
                peliculas.add(pelicula);
            } catch (Exception ex) { //Si hubo un error al momento de ingresar los datos
                System.out.println("ingrese un formato correcto: HH:mm");
                //Volvemos a pedir el dato en el numero de pelicula que hubo en un error
                i--;
            }
        }

        return peliculas;
    }

    /***
     * Pide al usuario el numero de peliculas que pretende ver en el maraton
     * @return el numero de peliculas que pretende ver el usuario en el maraton
     */
    public static int getNumeroDePeliculas() {
        Scanner scanner;
        int numeroDePeliculas = 0;
        boolean error = false;

        do {
            try {
                scanner = new Scanner(System.in);
                System.out.print("Ingresa el NUMERO de peliculas: ");
                numeroDePeliculas = scanner.nextInt();
                error = false;
                //Si planea ver menos de 1 pelicula (0)
                if (numeroDePeliculas < 1){
                    //arroja una excepcion para indicar que se necesita ver al menos una pelicula
                    throw new Exception();
                }
            } catch (Exception ex) {
                System.out.println("ingresa un numero positivo correcto de peliculas");
                error = true;
            }
        }while (error);

        return numeroDePeliculas;
    }
}

