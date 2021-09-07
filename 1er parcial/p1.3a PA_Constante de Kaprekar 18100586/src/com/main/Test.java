package com.main;

import com.numbers.Kaprekar;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Kaprekar kaprekar = new Kaprekar(getInNumber());
        while(!kaprekar.isValid()){
            kaprekar.setNumber(getInNumber());
        }
        kaprekar.table();
    }

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
