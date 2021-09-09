package com.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class NumbersText {
    private String numberAsString;
    private List<String> words;
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

    public NumbersText(String numberAsString) {
        this.numberAsString = numberAsString;
        this.words = new ArrayList<String>(Arrays.asList(numberAsString.split(" ")));
    }

    private int toInt(int parte, int index){
        int suma = 0;
        List<Integer> resultado = new ArrayList<>();
        if (parte == 1) {
            for (int i = (index - 1); i > -1; i--) {
                if (NUMBERS_TEXT_LIST.containsKey(this.words.get(i))) {
                    resultado.add(NUMBERS_TEXT_LIST.get(this.words.get(i)));
                    continue;
                }
            }
        }else {
            for (int i = (index + 1); i < this.words.size(); i++) {
                if (NUMBERS_TEXT_LIST.containsKey(this.words.get(i))) {
                    resultado.add(NUMBERS_TEXT_LIST.get(this.words.get(i)));
                    continue;
                }
            }
        }
        for (Integer numero : resultado) {
            suma += numero;
        }
        return suma;
    }

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
