package com.numbers;

import java.util.Arrays;

public class Kaprekar {
    private int number;
    private boolean isValid;

    public Kaprekar() {
        this.number = 0;
        this.isValidNumber();
    }

    public Kaprekar(int number) {
        this.number = number;
        this.isValidNumber();
    }

    private void isValidNumber(){
        if((this.number / 1000) > 9 || (this.number / 1000) < 1){
            this.isValid = false;
            System.out.println("Ingresa un numero Kaprekar valido");
            return;
        }
        this.isValid = true;
    }

    public void table(){
        if(this.isValid){

            int iteracion = 1;
            int[] digitos = new int[4];
            int resultNumber = this.number;

            while (resultNumber != 6174) {


                int numberToArray = resultNumber;
                int divisor = 1000;
                int greatestNumber = 0, lowerNumber = 0;

                for (int i = 0; i < digitos.length; i++) {
                    digitos[i] = (numberToArray / divisor);
                    numberToArray = (numberToArray % divisor);
                    divisor = (divisor / 10);
                }

                Arrays.sort(digitos);
                lowerNumber = Integer.parseInt(Arrays.toString(digitos).replaceAll("\\[|\\]|,|\\s", ""));

                for (int i = 0; i < digitos.length / 2; i++) {
                    int temp = digitos[i];
                    digitos[i] = digitos[digitos.length - i - 1];
                    digitos[digitos.length - i - 1] = temp;
                }

                greatestNumber = Integer.parseInt(Arrays.toString(digitos).replaceAll("\\[|\\]|,|\\s", ""));

                System.out.println(iteracion);
                System.out.println(resultNumber);
                System.out.println(greatestNumber);
                System.out.println(lowerNumber);
                resultNumber = greatestNumber - lowerNumber;
                System.out.println(resultNumber);
                iteracion++;

            }
        }
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
        this.isValidNumber();
    }

    public boolean isValid() {
        return this.isValid;
    }

    @Override
    public String toString() {
        return "Kaprekar{" +
                "number=" + number +
                ", isValid=" + isValid +
                '}';
    }
}
