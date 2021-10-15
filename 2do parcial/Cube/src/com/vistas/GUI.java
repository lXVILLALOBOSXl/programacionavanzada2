package com.vistas;

import sun.plugin.javascript.navig.Array;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame {

    private JButton zoomPlus, zoomMinus, xRotation, yRotation, zRotation, xTraslationP, yTraslationP, zTraslationP, xTraslationM, yTraslationM, zTraslationM;
    private static ArrayList<Double[][]> matricesEfecto = new ArrayList<>();
    private static ArrayList<Double[][]> matricesCubo = new ArrayList<>();
    private static Double [][] ZOOM;
    private static Double [][] TRASLATION;
    private static Double [][] X;
    private static Double [][] Y;
    private static Double [][] Z;
    private static Double [][] EFFECTS = new Double[4][4];
    private static Double X_TRASLATION = 500d;
    private static Double Y_TRASLATION = 400d;
    private static Double Z_TRASLATION = 0d;
    private static Double X_ZOOM = 1d;
    private static Double Y_ZOOM = 1d;
    private static Double Z_ZOOM = 1d;
    private static Double X_ROTATION = Math.toRadians(0);
    private static Double Y_ROTATION = Math.toRadians(0);
    private static Double Z_ROTATION = Math.toRadians(0);
    private static final Double Z_ANGLE = Math.toRadians(45);
    private static final Double COS_Z_ANGLE = (Math.cos(Z_ANGLE));
    private static final Double SIN_Z_ANGLE = (Math.sin(Z_ANGLE));




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
        xTraslationP = new JButton("X+");
        yTraslationP = new JButton("Y-");
        zTraslationP = new JButton("Z-");
        xTraslationM = new JButton("X-");
        yTraslationM = new JButton("Y+");
        zTraslationM = new JButton("Z+");


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
                                        .addComponent(xTraslationM,10,80,233)
                                        .addComponent(yTraslationP,10,80,233)
                                        .addComponent(zTraslationP,10,80,233)
                                            .addComponent(xTraslationP,10,80,233)
                                            .addComponent(yTraslationM,10,80,233)
                                            .addComponent(zTraslationM,10,80,233)
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
                                                        .addComponent(xTraslationM)
                                                        .addComponent(yTraslationP)
                                                        .addComponent(zTraslationP)
                                                        .addComponent(xTraslationP)
                                                        .addComponent(yTraslationM)
                                                        .addComponent(zTraslationM)
                                        )
                        )
        );

        matrix();

        zoomPlus.addActionListener((ActionEvent) -> {onClickZoomPlus();});

        zoomMinus.addActionListener((ActionEvent) -> {onClickZoomMinus();});

        xRotation.addActionListener((ActionEvent) -> {onClickXRotation();});

        yRotation.addActionListener((ActionEvent) -> {onClickYRotation();});

        zRotation.addActionListener((ActionEvent) -> {onClickZRotation();});

        xTraslationP.addActionListener((ActionEvent) -> {onClickXPTraslation();});

        yTraslationP.addActionListener((ActionEvent) -> {onClickYPTraslation();});

        zTraslationP.addActionListener((ActionEvent) -> {onClickZPTraslation();});

        xTraslationM.addActionListener((ActionEvent) -> {onClickXMTraslation();});

        yTraslationM.addActionListener((ActionEvent) -> {onClickYMTraslation();});

        zTraslationM.addActionListener((ActionEvent) -> {onClickZMTraslation();});

        setLayout(groupLayout);
        this.pack();

    }

    private void matrix() {
        fillMatrixEffects();
        createMainMatrix();
        getEffectMatrix();
        fillMatrixCube();
    }

    private void onClickZMTraslation() {
        Z_TRASLATION -= 10;
        this.repaint();
    }

    private void onClickYMTraslation() {
        Y_TRASLATION -= 10;
        this.repaint();
    }

    private void onClickXMTraslation() {
        X_TRASLATION -= 10;
        this.repaint();
    }

    private void onClickZPTraslation() {
        Z_TRASLATION += 10;
        this.repaint();
    }

    private void onClickYPTraslation() {
        Y_TRASLATION += 10;
        this.repaint();
    }

    private void onClickXPTraslation() {
        X_TRASLATION += 10;
        this.repaint();
    }

    private void onClickZRotation() {
        Z_ROTATION += 0.1;
        this.repaint();
    }

    private void onClickYRotation() {
        Y_ROTATION += 0.1;
        this.repaint();
    }

    private void onClickXRotation() {
        X_ROTATION += 0.1;
        this.repaint();
    }

    private void onClickZoomMinus() {
        X_ZOOM -= 0.1;
        Y_ZOOM -= 0.1;
        Z_ZOOM -= 0.1;
        this.repaint();
    }

    private void onClickZoomPlus() {
        X_ZOOM += 0.1;
        Y_ZOOM += 0.1;
        Z_ZOOM += 0.1;
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        ArrayList<Double> points = getPoints();

        for (int i = 4; i < points.size(); i+=4) {
            g.drawLine(points.get(i).intValue(), points.get(i+1).intValue(), points.get(i+2).intValue(), points.get(i+3).intValue());
        }

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

    private void createMainMatrix(){
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

    private void getEffectMatrix(){
        Double[][] resultado = matricesEfecto.get(0);
        for (int i = 0; i < matricesEfecto.size()-1; i++){
            resultado = multiplyArrays(resultado , matricesEfecto.get(i+1));
        }
        EFFECTS = resultado;
    }

    private ArrayList<Double> getPoints(){
        matrix();
        ArrayList<Double> points = new ArrayList<>();
        Double[][] resultado =  multiplyArrays(EFFECTS , matricesCubo.get(0));
        points.add(matricesCubo.get(0)[0][3]-matricesCubo.get(0)[2][3]*COS_Z_ANGLE);
        points.add(matricesCubo.get(0)[1][3]-matricesCubo.get(0)[2][3]*SIN_Z_ANGLE);

        for (int i = 0; i < matricesCubo.size()-1; i++){
            resultado =  multiplyArrays(resultado, matricesCubo.get(i+1));
            points.add(resultado[0][3]-resultado[2][3]*COS_Z_ANGLE);
            points.add(resultado[1][3]-resultado[2][3]*SIN_Z_ANGLE);
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
