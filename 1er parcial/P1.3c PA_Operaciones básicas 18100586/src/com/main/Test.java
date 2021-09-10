package com.main;

import com.text.NumbersText;

import java.util.Locale;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        //Creamos un objeto y lo inicalizamos con la cadena de texto que el usuario ingreso
        NumbersText numbersText = new NumbersText(getNumberAsString());

        numbersText.resultado();
    }

    /***
     * Le indica al usuario que ingrese su operacion, lo almacena y la convierte en minusculas
     * @return La cadena de texto de operacion ingresada por el usuario
     */
    public static String getNumberAsString(){
        Scanner scanner = new Scanner(System.in);
        String numberAsString = "";
        boolean error = false;
        do{
            System.out.print("Ingresa tu operacion como texto (numeros de 0 a 100): ");
            try {
                numberAsString = scanner.nextLine();
            }catch (Exception ex){
                error = true;
            }
        }while (error);
        return numberAsString.toLowerCase(Locale.ROOT);
    }
}
