package com.math;

import java.util.ArrayList;

/***
 * Realiza todos los calculos de matrices y regresa los puntos necesarios
 * para realizar las lineas
 */
public class Matrix {
    //Almacena las matrices (ZOOM, TRASLATION, X, Y, Z, ROTATION (X,Y,Z)) que son necesarias para tener la matriz de efectos
    private static ArrayList<Double[][]> matricesEfecto = new ArrayList<>();
    private static ArrayList<Double[][]> matricesCubo = new ArrayList<>();
    private static Double [][] ZOOM;
    private static Double [][] TRASLATION;
    private static Double [][] X;
    private static Double [][] Y;
    private static Double [][] Z;
    private static Double [][] EFFECTS = new Double[4][4];
    //por cuestiones de estatica lo iniciamos en esos valores para centrar el cubo cuando cargue por primera vez
    public static Double X_TRASLATION = 500d;
    public static Double Y_TRASLATION = 400d;
    public static Double Z_TRASLATION = 0d;
    public static Double X_ZOOM = 1d;
    public static Double Y_ZOOM = 1d;
    public static Double Z_ZOOM = 1d;
    public static Double X_ROTATION = Math.toRadians(0);
    public static Double Y_ROTATION = Math.toRadians(0);
    public static Double Z_ROTATION = Math.toRadians(0);
    //Por estetica, me agrado que el cubo estuviera a 45º
    private static final Double Z_ANGLE = Math.toRadians(45);
    //Almacena el seno y el coseno del angulo del cubo
    private static final Double COS_Z_ANGLE = (Math.cos(Z_ANGLE));
    private static final Double SIN_Z_ANGLE = (Math.sin(Z_ANGLE));

    /***
     * Inicializa y crea las matrices del cubo y de los efectos
     */
    public static void setMatrix(){
            fillMatrixEffects();
            createMainMatrix();
            getEffectMatrix();
            fillMatrixCube();
    }


    /***
     * LLena las matrices de los efectos
     */
    private static void fillMatrixEffects(){
        ZOOM = new Double[][]{
                {X_ZOOM, 0d, 0d, 0d},
                {0d, Y_ZOOM, 0d, 0d},
                {0d, 0d, Z_ZOOM, 0d},
                {0d, 0d, 0d, 1d}
        };
        TRASLATION = new Double[][]{
                {1d,0d,0d,X_TRASLATION},
                {0d,1d,0d,Y_TRASLATION},
                {0d,0d,1d,Z_TRASLATION},
                {0d,0d,0d,1d}
        };
        X = new Double[][]{
                {1d,0d,0d,0d},
                {0d,Math.cos(X_ROTATION),-(Math.sin(X_ROTATION)),0d},
                {0d,Math.sin(X_ROTATION),Math.cos(X_ROTATION),0d},
                {0d,0d,0d,1d}
        };
        Y = new Double[][]{
                {Math.cos(Y_ROTATION),0d,Math.sin(Y_ROTATION),0d},
                {0d,1d,0d,0d},
                {-(Math.sin(Y_ROTATION)),0d,Math.cos(Y_ROTATION),0d},
                {0d,0d,0d,1d}
        };
        Z = new Double[][]{
                {Math.cos(Z_ROTATION),-(Math.sin(Z_ROTATION)),0d,0d},
                {Math.sin(Z_ROTATION),Math.cos(Z_ROTATION),0d,0d},
                {0d,0d,1d,0d},
                {0d,0d,0d,1d}
        };
    }

    /**
     * Crea el arreglo de matrices de efectos
     */
    private static void createMainMatrix(){
        matricesEfecto.clear();
        matricesEfecto.add(new Double[][]{
                {1d,0d,0d,0d},
                {0d,1d,0d,0d},
                {0d,0d,1d,0d},
                {0d,0d,0d,1d}
        });
        matricesEfecto.add(TRASLATION);
        matricesEfecto.add(ZOOM);
        matricesEfecto.add(X);
        matricesEfecto.add(Y);
        matricesEfecto.add(Z);
    }

    /**
     * Obtinene la matriz resultante de efectos
     */
    private static void getEffectMatrix(){
        Double[][] resultado = matricesEfecto.get(0);
        for (int i = 0; i < matricesEfecto.size()-1; i++){
            resultado = multiply(resultado , matricesEfecto.get(i+1));
        }
        EFFECTS = resultado;
    }

    /***
     * Realiza las operaciones necesarias para sacar los puntos X y Y con los que
     * se trazan las rectas
     * @return Arreglo con puntos x,y
     */
    public static ArrayList<Double> getPoints(){
        //En caso de que se modifique el valor cuando presionemos un boton, es necesario
        //volver a modificar todas las matrices
        setMatrix();
        //Almacena los puntos para graficar las rectas
        ArrayList<Double> points = new ArrayList<>();
        //Guarda el resultado de las mutiplicaciones de matrices
        Double[][] resultado =  multiply(EFFECTS , matricesCubo.get(0));
        //agregamos los puntos de la matriz de inicio
        points.add(matricesCubo.get(0)[0][3]-matricesCubo.get(0)[2][3]*COS_Z_ANGLE);
        points.add(matricesCubo.get(0)[1][3]-matricesCubo.get(0)[2][3]*SIN_Z_ANGLE);

        //Agregamos los puntos de las matrices restantes
        for (int i = 0; i < matricesCubo.size()-1; i++){
            //Se va a ir guardando la multiplicacion, del resultado anterior por el siguiente
            resultado =  multiply(resultado, matricesCubo.get(i+1));
            //Se guardan los puntos X,Y del resultado
            points.add(resultado[0][3]-resultado[2][3]*COS_Z_ANGLE);
            points.add(resultado[1][3]-resultado[2][3]*SIN_Z_ANGLE);
        }

        return points;
    }

    /**
     * Crea y almacena un arreglo de matrices que son la serie de pasos para formar el cubo
     */
    private static void fillMatrixCube(){
        matricesCubo.clear();
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 0d},
                        {0d, 1d, 0d, 0d},
                        {0d, 0d, 1d, 0d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 100d},
                        {0d, 1d, 0d, 0d},
                        {0d, 0d, 1d, 0d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 0d},
                        {0d, 1d, 0d, 100d},
                        {0d, 0d, 1d, 0d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, -100d},
                        {0d, 1d, 0d, 0d},
                        {0d, 0d, 1d, 0d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 0d},
                        {0d, 1d, 0d, -100d},
                        {0d, 0d, 1d, 0d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 0d},
                        {0d, 1d, 0d, 0d},
                        {0d, 0d, 1d, 100d},
                        {0d, 0d, 0d, 1d}
                });

        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 100d},
                        {0d, 1d, 0d, 0d},
                        {0d, 0d, 1d, 0d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 0d},
                        {0d, 1d, 0d, 0d},
                        {0d, 0d, 1d, -100d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 0d},
                        {0d, 1d, 0d, 0d},
                        {0d, 0d, 1d, 100d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 0d},
                        {0d, 1d, 0d, 100d},
                        {0d, 0d, 1d, 0d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 0d},
                        {0d, 1d, 0d, 0d},
                        {0d, 0d, 1d, -100d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 0d},
                        {0d, 1d, 0d, 0d},
                        {0d, 0d, 1d, 100d},
                        {0d, 0d, 0d, 1d}
                });

        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, -100d},
                        {0d, 1d, 0d, 0d},
                        {0d, 0d, 1d, 0d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 0d},
                        {0d, 1d, 0d, 0d},
                        {0d, 0d, 1d, -100d},
                        {0d, 0d, 0d, 1d}
                });

        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 0d},
                        {0d, 1d, 0d, 0d},
                        {0d, 0d, 1d, 100d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 0d},
                        {0d, 1d, 0d, -100d},
                        {0d, 0d, 1d, 0d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 0d},
                        {0d, 1d, 0d, 0d},
                        {0d, 0d, 1d, -100d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 0d},
                        {0d, 1d, 0d, 100d},
                        {0d, 0d, 1d, 0d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 100d},
                        {0d, 1d, 0d, 0d},
                        {0d, 0d, 1d, 0d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 0d},
                        {0d, 1d, 0d, -100d},
                        {0d, 0d, 1d, 0d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, -100d},
                        {0d, 1d, 0d, 0d},
                        {0d, 0d, 1d, 0d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 0d},
                        {0d, 1d, 0d, 0d},
                        {0d, 0d, 1d, 100d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 100d},
                        {0d, 1d, 0d, 0d},
                        {0d, 0d, 1d, 0d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, -100d},
                        {0d, 1d, 0d, 0d},
                        {0d, 0d, 1d, 0d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 0d},
                        {0d, 1d, 0d, 100d},
                        {0d, 0d, 1d, 0d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 100d},
                        {0d, 1d, 0d, 0d},
                        {0d, 0d, 1d, 0d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 0d},
                        {0d, 1d, 0d, 0d},
                        {0d, 0d, 1d, -100d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 0d},
                        {0d, 1d, 0d, -100d},
                        {0d, 0d, 1d, 0d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, -100d},
                        {0d, 1d, 0d, 0d},
                        {0d, 0d, 1d, 0d},
                        {0d, 0d, 0d, 1d}
                });
        matricesCubo.add(
                new Double[][]{
                        {1d, 0d, 0d, 100d},
                        {0d, 1d, 0d, 0d},
                        {0d, 0d, 1d, 0d},
                        {0d, 0d, 0d, 1d}
                });
    }

    /**
     * Multiplica matrices de 4 x 4
     * @param matrixA
     * @param matrixB
     * @return Resultado de la matriz a por la matriz b
     */
    private static Double[][] multiply(Double[][] matrixA, Double[][] matrixB){
        Double[][] resultado = new Double[4][4];
        int columnasResultado = 0;
        int filasAux = 0;

        for (int filas = 0; filas < 4; filas++) {
            for (int subFilas = 0; subFilas < 4; subFilas++) {
                Double result = 0d;
                for (int columnas = 0; columnas < 4; columnas++) {
                    result += (matrixA[filasAux][columnas] * matrixB[columnas][subFilas]);
                }
                resultado[filas][columnasResultado++] = result;
            }
            filasAux++;
            columnasResultado = 0;
        }

        return resultado;
    }

}
