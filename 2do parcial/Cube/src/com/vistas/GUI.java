package com.vistas;

import com.math.Matrix;
import sun.plugin.javascript.navig.Array;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame {

    private JButton zoomPlus, zoomMinus, xRotationP, yRotationP, zRotationP, xRotationM, yRotationM, zRotationM, xTraslationP, yTraslationP, zTraslationP, xTraslationM, yTraslationM, zTraslationM;

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
        xRotationP = new JButton("X+º");
        yRotationP = new JButton("Y+º");
        zRotationP = new JButton("Z+º");
        xRotationM = new JButton("X-º");
        yRotationM = new JButton("Y-º");
        zRotationM = new JButton("Z-º");
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
                                        .addComponent(xRotationP,10,80,233)
                                        .addComponent(yRotationP,10,80,233)
                                        .addComponent(zRotationP,10,80,233)
                                            .addComponent(xRotationM,10,80,233)
                                            .addComponent(yRotationM,10,80,233)
                                            .addComponent(zRotationM,10,80,233)
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
                                                        .addComponent(xRotationP)
                                                        .addComponent(yRotationP)
                                                        .addComponent(zRotationP)
                                                        .addComponent(xRotationM)
                                                        .addComponent(yRotationM)
                                                        .addComponent(zRotationM)
                                                        .addComponent(xTraslationM)
                                                        .addComponent(yTraslationP)
                                                        .addComponent(zTraslationP)
                                                        .addComponent(xTraslationP)
                                                        .addComponent(yTraslationM)
                                                        .addComponent(zTraslationM)
                                        )
                        )
        );

        Matrix.setMatrix();

        zoomPlus.addActionListener((ActionEvent) -> {onClickZoomPlus();});

        zoomMinus.addActionListener((ActionEvent) -> {onClickZoomMinus();});

        xRotationP.addActionListener((ActionEvent) -> {onClickXRotationP();});

        yRotationP.addActionListener((ActionEvent) -> {onClickYRotationP();});

        zRotationP.addActionListener((ActionEvent) -> {onClickZRotationP();});

        xRotationM.addActionListener((ActionEvent) -> {onClickXRotationM();});

        yRotationM.addActionListener((ActionEvent) -> {onClickYRotationM();});

        zRotationM.addActionListener((ActionEvent) -> {onClickZRotationM();});

        xTraslationP.addActionListener((ActionEvent) -> {onClickXPTraslation();});

        yTraslationP.addActionListener((ActionEvent) -> {onClickYPTraslation();});

        zTraslationP.addActionListener((ActionEvent) -> {onClickZPTraslation();});

        xTraslationM.addActionListener((ActionEvent) -> {onClickXMTraslation();});

        yTraslationM.addActionListener((ActionEvent) -> {onClickYMTraslation();});

        zTraslationM.addActionListener((ActionEvent) -> {onClickZMTraslation();});

        setLayout(groupLayout);
        this.pack();

    }



    private void onClickZMTraslation() {
        Matrix.Z_TRASLATION -= 10;
        this.repaint();
    }

    private void onClickYMTraslation() {
        Matrix.Y_TRASLATION -= 10;
        this.repaint();
    }

    private void onClickXMTraslation() {
        Matrix.X_TRASLATION -= 10;
        this.repaint();
    }

    private void onClickZPTraslation() {
        Matrix.Z_TRASLATION += 10;
        this.repaint();
    }

    private void onClickYPTraslation() {
        Matrix.Y_TRASLATION += 10;
        this.repaint();
    }

    private void onClickXPTraslation() {
        Matrix.X_TRASLATION += 10;
        this.repaint();
    }

    private void onClickZRotationP() {
        Matrix.Z_ROTATION += 0.1;
        this.repaint();
    }

    private void onClickYRotationP() {
        Matrix.Y_ROTATION += 0.1;
        this.repaint();
    }

    private void onClickXRotationP() {
        Matrix.X_ROTATION += 0.1;
        this.repaint();
    }

    private void onClickZRotationM() {
        Matrix.Z_ROTATION -= 0.1;
        this.repaint();
    }

    private void onClickYRotationM() {
        Matrix.Y_ROTATION -= 0.1;
        this.repaint();
    }

    private void onClickXRotationM() {
        Matrix.X_ROTATION -= 0.1;
        this.repaint();
    }

    private void onClickZoomMinus() {
        if(Matrix.X_ZOOM > 0.2) {
            Matrix.X_ZOOM -= 0.1;
            Matrix.Y_ZOOM -= 0.1;
            Matrix.Z_ZOOM -= 0.1;
        }
        this.repaint();
    }

    private void onClickZoomPlus() {
        Matrix.X_ZOOM += 0.1;
        Matrix.Y_ZOOM += 0.1;
        Matrix.Z_ZOOM += 0.1;
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        ArrayList<Double> points = Matrix.getPoints();

        for (int i = 4; i < points.size(); i+=4) {
            g.drawLine(points.get(i).intValue(), points.get(i+1).intValue(), points.get(i+2).intValue(), points.get(i+3).intValue());
        }

    }
}
