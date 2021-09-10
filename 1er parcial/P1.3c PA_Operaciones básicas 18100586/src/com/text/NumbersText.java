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
        put("diecisiete",17);
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
        put("ciento",100);
        put("cien",100);
        //A partir de aqui son los posibles datos invalidos que el usuario nos puede ingresar, es necesario
        //agregarlos para identificarlos y decir que esta fuera del rango
        put("doscientos",200);
        put("trescientos",300);
        put("cuatrocientos",400);
        put("quinientos",500);
        put("seicientos",600);
        put("setecientos",700);
        put("ochoientos",800);
        put("novecientos",900);
        put("mil",1000);
        put("millon",1000);
        put("millones",1000);
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
        //Se utiliza para formar el numero entero, sumando las centenas decenas y unidades de texto
        int suma = 0;
        //Guarda el numero de coincidencias de numeros encontrados en words
        int encontrados = 0;
        //Almacena por unidades, decenas y centenas las distintas partes de la operacion
        List<Integer> resultado = new ArrayList<>();
        if (parte == 1) {
            //La parte es la primera por lo que tiene que recorrer de la posicion donde encontro el operador hacia atras
            for (int i = (index - 1); i > -1; i--) {
                //Si enceuntra la palabra dentro del arreglo de palabras
                if (NUMBERS_TEXT_LIST.containsKey(this.words.get(i))) {
                    encontrados++;
                    //Lo agrega al arreglo donde se almacenan las partes enteras
                    resultado.add(NUMBERS_TEXT_LIST.get(this.words.get(i)));
                    continue;
                }
            }
        }else {
            //La parte es la primera por lo que tiene que recorrer de la posicion donde encontro el operador hacia el final
            for (int i = (index + 1); i < this.words.size(); i++) {
                if (NUMBERS_TEXT_LIST.containsKey(this.words.get(i))) {
                    encontrados++;
                    resultado.add(NUMBERS_TEXT_LIST.get(this.words.get(i)));
                    continue;
                }
            }
        }

        //Si no se encontro nada quiere decir que esta incorrecto
        if(encontrados == 0){
            //retorna esta cantidad para decir que vuelva a ingresar su cantidad
            return 10001;
        }else{
            //termina de convertir las partes a numeros enteros
            for (Integer numero : resultado) {
                //si hubo algun numero idenificado como mayor a 100, es incorrecto
                if(numero > 100){
                    return 10001;
                }else{
                    //realizamos la suma de centenas decenas unidades
                    suma += numero;
                }
            }
            //Si al realizar la suma fue identificado correcto el ciento como 100, quiere decir que el numero que ingreso fue (101 - 199)
            //Por lo cual se comprueba si la suma fue un numero del rango anterior
            if(suma > 100){
                return 10001;
            }
        }
        return suma;
    }

    /***
     * Se encarcarga de recorrer el arreglo de palabras y si encuentra un operador
     * manda a realizar la operacion correspondiente
     * @return Resultado de la operacion
     */
    public void resultado(){
        int encontrados = 0;
        for (int i = 0; i < this.words.size(); i++) {
            switch (this.words.get(i)){
                case "mas":
                    encontrados++;
                    System.out.println(resultInText(this.toInt(1,i) + this.toInt(2,i)));
                    break;
                case "menos":
                    encontrados++;
                    System.out.println(resultInText(this.toInt(1,i) - this.toInt(2,i)));
                    break;
                case "por":
                    encontrados++;
                    System.out.println(resultInText(this.toInt(1,i) * this.toInt(2,i)));
                    break;
                case "entre":
                    encontrados++;
                    //Intenta hacer la division por si el denominador es 0
                    try {
                        System.out.println(resultInText(this.toInt(1,i) / this.toInt(2,i)));
                    }catch (Exception ex){
                        //Si el denominador fue 0, el resultado es infinito
                        System.out.println("Infinito");
                    }
                    break;
                default:
                    break;
            }
        }
        //Si no se encontro un operador valido
        if(encontrados == 0){
            System.out.println("Ingrese una operacion correcta y dentro del rango (0 - 100)");
        }
    }

    /**
     * Transforma un numero entero a texto
     * @param resultado es el equivalente numerico de la operacion ingresada por el usuario
     * @return resultado de la operacion en texto
     */
    private String resultInText(int resultado) {
        String resultadoEnTexto = "";
        //Si el resultado es mayor a 10000 quiere decir que el usuario ingreso una cantidad mayor a 100
        if (resultado > 10000) {
                resultadoEnTexto = "Ingrese una operacion correcta y dentro del rango (0 - 100)";
        } else {
            //identifico si hay miles
            if ((resultado / 1000) > 0) {
                //Si es 1000 no se antepone ningun numero solo se contatena a mil el resto
                if ((resultado / 1000) == 1) {
                    resultadoEnTexto = ("mil " + resultInText(resultado % 1000));
                } else {
                    //Si hay miles pero no es mil se antepone el numero de miles y se concatena el resto
                    resultadoEnTexto = (resultInText(resultado / 1000) + "mil " + resultInText(resultado % 1000));
                }
            } else {
                //identifico si hay cientos
                if ((resultado / 100) > 0) {
                    switch ((resultado / 100)) {
                        case 1:
                            //Si es 100 y no hay resto no se antepone ningun numero solo es cien
                            if(resultado % 100 == 0){
                                resultadoEnTexto = ("cien");
                            }else{
                                //Si es 100 y hay resto no se antepone ningun numero solo se contatena a ciento el resto
                                resultadoEnTexto = ("ciento " + resultInText(resultado % 100));
                            }
                            break;
                        case 5:
                            //Si es 500 y hay resto no se antepone ningun numero solo se contatena a quinientos el resto
                            resultadoEnTexto = ("quinientos " + resultInText(resultado % 100));
                            break;
                        case 9:
                            //Si es 900 y hay resto no se antepone ningun numero solo se contatena a nocecientos el resto
                            resultadoEnTexto = ("novecientos " + resultInText(resultado % 100));
                            break;
                        default:
                            //Si es cientos y hay resto se antepone el numero de cientos y se concatena el resto
                            resultadoEnTexto = (resultInText(resultado / 100) + "\bcientos " + resultInText(resultado % 100));
                            break;
                    }
                }else{
                    //identificamos si hay decenas
                    if ((resultado / 10) > 0) {
                        //Si son las primeras 10 decenas son casos especiales por que por ejemplo no hay diez y uno
                        if(resultado / 10 == 1) {
                            switch ((resultado % 10)) {
                                case 0:
                                    resultadoEnTexto = ("diez");
                                    break;
                                case 1:
                                    resultadoEnTexto = ("once" );
                                    break;
                                case 2:
                                    resultadoEnTexto = ("doce" );
                                    break;
                                case 3:
                                    resultadoEnTexto = ("trece");
                                    break;
                                case 4:
                                    resultadoEnTexto = ("catorce" );
                                    break;
                                case 5:
                                    resultadoEnTexto = ("quince" );
                                    break;
                                case 6:
                                    resultadoEnTexto = ("dieciseis" );
                                    break;
                                case 7:
                                    resultadoEnTexto = ("diecisiete" );
                                    break;
                                case 8:
                                    resultadoEnTexto = ("dieciocho" );
                                    break;
                                case 9:
                                    resultadoEnTexto = ("diecinueve");
                                    break;
                            }
                        }else{ //Si no estan en las primeras decenas
                            switch ((resultado / 10)) {
                                case 2:
                                    //Si es veinte y no hay ningun resto
                                    if(resultado % 20 == 0){
                                        resultadoEnTexto = ("veinte");
                                    }else{
                                        //Si es veinte y hay resto se aantepone veinti y se agrega el resto
                                        resultadoEnTexto = ("veinti" + resultInText(resultado % 10));
                                    }
                                    break;
                                //Si es cualquier otra decena se antepone su nombre  (treinta, cuarenta etc) y se concatena el resto, si lo hay si no, solo se queda el nombre de decena
                                case 3:
                                    resultadoEnTexto = ("treinta");
                                    if(!(resultado % 30 == 0)){
                                        resultadoEnTexto = ("treinta y " + resultInText(resultado % 10));
                                    }
                                    break;
                                case 4:
                                    resultadoEnTexto = ("cuarenta");
                                    if(!(resultado % 40 == 0)){
                                        resultadoEnTexto = ("cuarenta y " + resultInText(resultado % 10));
                                    }
                                    break;
                                case 5:
                                    resultadoEnTexto = ("cincuenta");
                                    if(!(resultado % 50 == 0)){
                                        resultadoEnTexto = ("cincuenta y " + resultInText(resultado % 10));
                                    }
                                    break;
                                case 6:
                                    resultadoEnTexto = ("sesenta");
                                    if(!(resultado % 60 == 0)){
                                        resultadoEnTexto = ("sesenta y " + resultInText(resultado % 10));
                                    }
                                    break;
                                case 7:
                                    resultadoEnTexto = ("setenta");
                                    if(!(resultado % 70 == 0)){
                                        resultadoEnTexto = ("setenta y " + resultInText(resultado % 10));
                                    }
                                    break;
                                case 8:
                                    resultadoEnTexto = ("ochenta");
                                    if(!(resultado % 80 == 0)){
                                        resultadoEnTexto = ("ochenta y " + resultInText(resultado % 10));
                                    }
                                    break;
                                case 9:
                                    resultadoEnTexto = ("noventa");
                                    if(!(resultado % 90 == 0)){
                                        resultadoEnTexto = ("noventa y " + resultInText(resultado % 10));
                                    }
                                    break;
                            }
                        }
                    }else{
                        //identificamos si hay unidades incluyendo el 0
                        if((resultado / 1) > 0){
                            switch ((resultado / 1)) {
                                case 1:
                                    resultadoEnTexto = ("uno ");
                                    break;
                                case 2:
                                    resultadoEnTexto = ("dos ");
                                    break;
                                case 3:
                                    resultadoEnTexto = ("tres ");
                                    break;
                                case 4:
                                    resultadoEnTexto = ("cuatro ");
                                    break;
                                case 5:
                                    resultadoEnTexto = ("cinco ");
                                    break;
                                case 6:
                                    resultadoEnTexto = ("seis ");
                                    break;
                                case 7:
                                    resultadoEnTexto = ("siete ");
                                    break;
                                case 8:
                                    resultadoEnTexto = ("ocho ");
                                    break;
                                case 9:
                                    resultadoEnTexto = ("nueve ");
                                    break;
                            }
                        }else{
                            return "";
                        }
                    }
                }
            }
        }

        return resultadoEnTexto;
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
