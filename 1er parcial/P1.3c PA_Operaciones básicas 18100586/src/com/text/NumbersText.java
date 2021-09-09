package com.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class NumbersText {
    //Almacena cadena de texto ingresada por usuario
    private String numberAsString;
    //Almacena cadena de texto ingresada por usuario dividida en palabras
    private List<String> words;
    /**
     * ALmacena el nombre del numero como llave y su equivalente en tipo
     * entero (Son todas las posibles variaciones que existen en el lenguaje español)
     * 16 no puede ser dies y seis, tiene que ser dieciceis y es una variante mas
     * 31 no es variante por que se puede formar de treinta y uno
     */
    private static final HashMap<String,Integer> NUMBERS_TEXT_LIST = new HashMap<String, Integer>(){{
        put("cero",0);
        put("uno",1);
        put("dos",2);
        put("tres",3);
        put("cuatro",4);
        put("cinco",5);
        put("seis",6);
        put("siete",7);
        put("ocho",8);
        put("nueve",9);
        put("diez",10);
        put("once",11);
        put("doce",12);
        put("trece",13);
        put("catorce",14);
        put("quince",15);
        put("dieciseis",16);
        put("dieciocho",18);
        put("diecinueve",19);
        put("veinte",20);
        put("veintiuno",21);
        put("veintidos",22);
        put("veintitres",23);
        put("veinticuatro",24);
        put("veinticinco",25);
        put("veintiseis",26);
        put("veintisiete",27);
        put("veintiocho",28);
        put("veintinueve",29);
        put("treinta",30);
        put("cuarenta",40);
        put("cincuenta",50);
        put("sesenta",60);
        put("setenta",70);
        put("ochenta",80);
        put("noventa",90);
        put("cien",100);
        put("doscientos",200);
        put("trescientos",300);
        put("cuatrocientos",400);
        put("quinientos",500);
        put("seicientos",600);
        put("setecientos",700);
        put("ochoientos",800);
        put("novecientos",900);
    }};

    /***
     * Inicializa la propiedad numberAsString con la cadena ingresada
     * Divide por palabras la frase ingresada por el usuario y lo guarda en su propiedad
     * Words
     * @param numberAsString Recibe la cadena de texto de operacion ingresada por el usuario
     */
    public NumbersText(String numberAsString) {
        this.numberAsString = numberAsString;
        this.words = new ArrayList<String>(Arrays.asList(numberAsString.split(" ")));
    }

    /***
     * Dada una cadena de texto lo interpreta y convierte a entero
     * @param parte es el numero que esta antes o despues del operador
     * @param index es donde se quedo recorrienddo el arreglo de palabras donde se busca el operador
     * @return el numero entero de la parte en que se encuentra
     */
    private int toInt(int parte, int index){
        int suma = 0;
        //Almacena por unidades, decenas y centenas las distintas partes de la operacion
        List<Integer> resultado = new ArrayList<>();
        if (parte == 1) {
            //La parte es la primera por lo que tiene que recorrer de la posicion donde encontro el operador hacia atras
            for (int i = (index - 1); i > -1; i--) {
                //Si enceuntra la palabra dentro del arreglo de palabras
                if (NUMBERS_TEXT_LIST.containsKey(this.words.get(i))) {
                    //Lo agrega al arreglo donde se almacenan las partes enteras
                    resultado.add(NUMBERS_TEXT_LIST.get(this.words.get(i)));
                    continue;
                }
            }
        }else {
            //La parte es la primera por lo que tiene que recorrer de la posicion donde encontro el operador hacia el final
            for (int i = (index + 1); i < this.words.size(); i++) {
                if (NUMBERS_TEXT_LIST.containsKey(this.words.get(i))) {
                    resultado.add(NUMBERS_TEXT_LIST.get(this.words.get(i)));
                    continue;
                }
            }
        }
        //termina de convertir las partes a numeros enteros
        for (Integer numero : resultado) {
            //realizamos la suma de centenas decenas unidades
            suma += numero;
        }
        return suma;
    }

    /***
     * Se encarcarga de recorrer el arreglo de palabras y si encuentra un operador
     * manda a realizar la operacion correspondiente
     * @return Resultado de la operacion
     */
    public int resultado(){
        for (int i = 0; i < this.words.size(); i++) {
            switch (this.words.get(i)){
                case "mas":
                    return this.toInt(1,i) + this.toInt(2,i);
                case "menos":
                    return this.toInt(1,i) - this.toInt(2,i);
                case "por":
                    return this.toInt(1,i) * this.toInt(2,i);
                case "entre":
                    return this.toInt(1,i) / this.toInt(2,i);
                default:
                    break;
            }
        }
        return 0;
    }

    public String getNumberAsString() {
        return numberAsString;
    }

    public void setNumberAsString(String numberAsString) {
        this.numberAsString = numberAsString;
    }

    @Override
    public String toString() {
        return "Convert{" +
                "numberAsString='" + numberAsString + '\'' +
                '}';
    }
}
