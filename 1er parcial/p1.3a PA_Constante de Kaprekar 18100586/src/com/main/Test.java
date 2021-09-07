package com.main;

import com.numbers.Kaprekar;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        //Inicializamos un objeto de la clase Kaprekar con el numero correcto ingresado
        Kaprekar kaprekar = new Kaprekar(getInNumber());
        //Mientras el numero no sea un kaprekar valido
        while(!kaprekar.isValid()){
            //se pedira ingresar el numero de nuevo y se asignara uno correcto
            kaprekar.setNumber(getInNumber());
        }
        //Una vez que es valido, generamos el algoritmo para llegar desde el numero
        //ingresado hasra Kaprekar
        kaprekar.table();
    }

    //Le indica al usuario que ingrese un numero, si el formato de numero es
    //incorrecto regresa un 0 y si es correcto regresa el numero ingresado
    public static int getInNumber(){
        Scanner scanner = new Scanner(System.in);
        int number = 0;

        System.out.print("Please type a number: ");
        try{
            number = scanner.nextInt();
        }catch (Throwable ex){
            System.out.println("Ingrese solo un numero entero valido");
            getInNumber();
        }
        return number;
    }
}
