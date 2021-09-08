package com.numbers;

import java.util.Arrays;

public class Kaprekar {
    //Guarda el numero inicial que el usuario ingreso
    private int number;
    //Guarda el estado del numero Kaprekar guardado
    private boolean isValid;

    //Para programadores: Puedes inicializar un kaprekar sin un numero
    //pero este siempre sera 0 y por ende un kaprekar invalido
    public Kaprekar() {
        this.number = 0;
        this.isValid = false;
    }

    //Para programadores: Puedes inicializar un kaprekar con un numero ingresado
    //Sera igual a dicho numero y despues se verifica si es valido y se asigna su estado
    public Kaprekar(int number) {
        this.number = number;
        this.isValidNumber();
    }

    //Determina si el kaprekar es valido
    private void isValidNumber(){
        //Si es mayor a 9999 o menor a 1, indica que no es valido
        if((this.number / 1000) > 9 || this.number < 1){
            this.isValid = false;
            System.out.println("Ingresa un numero Kaprekar valido");
            return;
        }else {
            //Verifica que haya mas de 2 digitos distintos
            String number = Integer.toString(this.number);
            int coincidencias = 0;
            for (int i = 0; i < (number.length() - 1); i++) {
                if (number.charAt(i) == number.charAt(i + 1)) {
                    coincidencias++;
                }
            }
            //Si hay 4 digitos iguales, el numero kaprekar no es valido
            if (coincidencias > 2) {
                this.isValid = false;
                System.out.println("Ingresa un numero Kaprekar valido");
                return;
            }
        }
        this.isValid = true;
    }

    //Procesa el algoritmo u muestra los numeros para llegar al numero kaprekar
    public void table(){
        //Si es valido se puede aplicar el algoritmo
        if(this.isValid){
            int iteracion = 1;
            //GUarda el numero que esta siendo procesado en un arreglo
            int[] digitos = new int[4];
            //Almacena para la primera iteracion el numero ingresado
            int resultNumber = this.number;
            System.out.print("\nIteracion   NI      NP       NS     Nuevo NI");

            //Mientras no se llegue al numero Kaprekar se hace el algoritmo
            while (resultNumber != 6174) {

                //Guarda el resultado de las restas para despues pasarlo en un array
                int numberToArray = resultNumber;
                //Se utiliza para dividir el numero en milares, centenas, decenas y unidades
                int divisor = 1000;
                //Almacenan el numero mas y menor que se puede formar con el numero que se esta procesando
                int greatestNumber = 0, lowerNumber = 0;

                //Guarda el numero procesado en un arreglo
                for (int i = 0; i < digitos.length; i++) {
                    digitos[i] = (numberToArray / divisor);
                    numberToArray = (numberToArray % divisor);
                    divisor = (divisor / 10);
                }

                //Ordena los numeros de menor a mayor del arreglo
                Arrays.sort(digitos);
                //Casteamos el arreglo de enteros a un entero
                lowerNumber = Integer.parseInt(Arrays.toString(digitos).replaceAll("\\[|\\]|,|\\s", ""));

                //Hacemos una especie de reverse del numero mas pequeño que se pudo formar
                for (int i = 0; i < digitos.length / 2; i++) {
                    int temp = digitos[i];
                    digitos[i] = digitos[digitos.length - i - 1];
                    digitos[digitos.length - i - 1] = temp;
                }

                greatestNumber = Integer.parseInt(Arrays.toString(digitos).replaceAll("\\[|\\]|,|\\s", ""));

                //Mostramos al usuario la informacion
                System.out.print("\n" + iteracion);
                System.out.print("          " + resultNumber);
                System.out.print("     " + greatestNumber);
                System.out.print("     " + lowerNumber);
                resultNumber = greatestNumber - lowerNumber;
                System.out.print("     " + resultNumber);
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
