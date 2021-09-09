package com.main;

import com.text.NumbersText;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        NumbersText numbersText = new NumbersText(getNumberAsString());
        System.out.println(numbersText.resultado());
    }

    public static String getNumberAsString(){
        System.out.print("Please, type your operation as Text: ");
        Scanner scanner = new Scanner(System.in);
        String numberAsString = "";
        boolean error = false;
        do{
            try {
                numberAsString = scanner.nextLine();
            }catch (Exception ex){
                error = true;
            }
        }while (error);
        return numberAsString;
    }
}
