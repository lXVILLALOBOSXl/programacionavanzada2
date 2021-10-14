package com.vistas;

import sun.plugin.javascript.navig.Array;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame {

    private JButton zoomPlus, zoomMinus, xRotation, yRotation, zRotation, xTraslation, yTraslation, zTraslation;
    private static ArrayList<Double[][]> matricesEfecto = new ArrayList<>();
    private static ArrayList<Double[][]> matricesCubo = new ArrayList<>();
    private static Double [][] ZOOM;
    private static Double [][] TRASLATION;
    private static Double [][] X;
    private static Double [][] Y;
    private static Double [][] Z;
    private static Double [][] EFFECTS = new Double[4][4];
    private static Double X_TRASLATION = 0d;
    private static Double Y_TRASLATION = 0d;
    private static Double Z_TRASLATION = 0d;
    private static Double X_ZOOM = 1d;
    private static Double Y_ZOOM = 1d;
    private static Double Z_ZOOM = 1d;
    private static Double X_ROTATION = Math.toRadians(0);
    private static Double Y_ROTATION = Math.toRadians(0);
    private static Double Z_ROTATION = Math.toRadians(0);
    private static final Double Z_ANGLE = Math.toRadians(45);
    private static final int COS_Z_ANGLE = ((int)Math.cos(Z_ANGLE));
    private static final int SIN_Z_ANGLE = ((int)Math.sin(Z_ANGLE));




    public GUI(){
        super();
        inicializa();
    }

    protected void inicializa(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1000,5000));
        this.setTitle("18100586");
        this.setLocationRelativeTo(null);

        zoomPlus = new JButton("+");
        zoomMinus = new JButton("-");
        xRotation = new JButton("Xº");
        yRotation = new JButton("Yº");
        zRotation = new JButton("Zº");
        xTraslation = new JButton("X");
        yTraslation = new JButton("Y");
        zTraslation = new JButton("Z");


        GroupLayout groupLayout;
        groupLayout = new GroupLayout(this.getContentPane());

        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);

        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup()
                    .addGroup(
                           groupLayout.createParallelGroup()
                            .addGroup(
                                    groupLayout.createSequentialGroup()
                                        .addComponent(zoomPlus,10,80,233)
                                        .addComponent(zoomMinus,10,80,233)
                                        .addComponent(xRotation,10,80,233)
                                        .addComponent(yRotation,10,80,233)
                                        .addComponent(zRotation,10,80,233)
                                        .addComponent(xTraslation,10,80,233)
                                        .addComponent(yTraslation,10,80,233)
                                        .addComponent(zTraslation,10,80,233)
                            )
                    )
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addGroup(
                                groupLayout.createSequentialGroup()
                                        .addGroup(
                                                groupLayout.createParallelGroup()
                                                        .addComponent(zoomPlus)
                                                        .addComponent(zoomMinus)
                                                        .addComponent(xRotation)
                                                        .addComponent(yRotation)
                                                        .addComponent(zRotation)
                                                        .addComponent(xTraslation)
                                                        .addComponent(yTraslation)
                                                        .addComponent(zTraslation)
                                        )
                        )
        );

        fillMatrixEffects();
        createMainMatrix();
        getEffectMatrix();
        fillMatrixCube();

        zoomPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        zoomMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        xRotation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        yRotation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        zRotation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        xTraslation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        yTraslation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        zTraslation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        setLayout(groupLayout);
        this.pack();

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        ArrayList<Integer> points = getPoints();
        for (int i = 0; i < points.size()-3; i++) {
            g.drawLine(points.get(i), points.get(i+1), points.get(i+2), points.get(i+3));
        }

        /*g.draw3DRect((this.getWidth()/2)-250,(this.getHeight()/2)-250,200,200,true);*/
        /*g.draw3DRect((this.getWidth()/2)-350,(this.getHeight()/2)-350,200,200,false);*/

        /*g.drawOval((this.getWidth()/2)-250,(this.getHeight()/2)-250,500,500)*/;
    }

    private void fillMatrixEffects(){
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

    private void fillMatrixCube(){
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
                });matricesCubo.add(
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

    }

    private void createMainMatrix(){
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

    private void getEffectMatrix(){
        for (int i = 0; i < matricesEfecto.size()-1; i++){
            matricesEfecto.set( (i) , multiplyArrays(matricesEfecto.get(i) , matricesEfecto.get(i+1)) );
        }
        EFFECTS = matricesEfecto.get(matricesEfecto.size()-3);
    }

    private ArrayList<Integer> getPoints(){
        ArrayList<Integer> points = new ArrayList<>();
        matricesCubo.set( (0) , multiplyArrays(EFFECTS , matricesCubo.get(0)) );
        points.add(matricesCubo.get(0)[0][3].intValue()-matricesCubo.get(0)[2][3].intValue()*COS_Z_ANGLE);
        points.add(matricesCubo.get(0)[1][3].intValue()-matricesCubo.get(0)[2][3].intValue()*SIN_Z_ANGLE);
        points.add(matricesCubo.get(1)[0][3].intValue()-matricesCubo.get(0)[2][3].intValue()*COS_Z_ANGLE);
        points.add(matricesCubo.get(1)[1][3].intValue()-matricesCubo.get(0)[2][3].intValue()*SIN_Z_ANGLE);
        int filas = 0;
        for (int i = 0; i < matricesCubo.size()-1; i++){
            matricesCubo.set( (i) , multiplyArrays(matricesCubo.get(i) , matricesCubo.get(i+1)) );
            points.add(matricesCubo.get(i)[0][3].intValue()-matricesCubo.get(i)[2][3].intValue()*COS_Z_ANGLE);
            points.add(matricesCubo.get(i)[1][3].intValue()-matricesCubo.get(i)[2][3].intValue()*SIN_Z_ANGLE);
            /*if(filas == 3){
                filas = 0;
            }
            matricesCubo.set( (i) , multiplyArrays(matricesCubo.get(i) , matricesCubo.get(i+1)) );
            points.add(matricesCubo.get(i)[filas][3].intValue());
            filas++;*/
        }
        return points;
    }

    private Double[][] multiplyArrays(Double[][] matrixA, Double[][] matrixB){
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
