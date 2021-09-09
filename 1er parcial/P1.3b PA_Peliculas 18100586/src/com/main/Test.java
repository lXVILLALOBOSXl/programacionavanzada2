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

    public static Duration getDuracionMaraton() {
        Scanner scanner = new Scanner(System.in);
        Duration duracion = Duration.ZERO;
        boolean error = false;

        do {
            try {
                scanner = new Scanner(System.in);
                System.out.print("Ingrese el TIEMPO (HH:mm) del Maraton: ");
                duracion = Duration.between(LocalTime.MIN, LocalTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("H:mm")));
                error = false;
            } catch (Exception ex) {
                System.out.println("ingrese un formato correcto: HH:mm");
                error = true;
            }
        }while (error);


        return duracion;
    }

    public static List<Pelicula> getListaDePeliculas(int numeroDePeliculas) {
        List<Pelicula> peliculas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < numeroDePeliculas; i++) {
            Pelicula pelicula = new Pelicula();
            try {
                System.out.print("\nIngrese el TIEMPO (HH:mm) de la pelicula " + (i + 1) + " : ");
                pelicula.setDuracion(Duration.between(LocalTime.MIN, LocalTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("H:mm"))));
                System.out.print("Ingrese el NOMBRE de la pelicula " + (i + 1) + " : ");
                pelicula.setNombre(scanner.nextLine());
                peliculas.add(pelicula);
            } catch (Exception ex) {
                System.out.println("ingrese un formato correcto: HH:mm");
                i--;
            }
        }

        return peliculas;
    }

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
                if (numeroDePeliculas < 1){
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

