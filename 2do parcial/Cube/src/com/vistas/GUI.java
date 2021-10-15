package com.vistas;

import com.math.Matrix;
import sun.plugin.javascript.navig.Array;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame {

    private JButton zoomPlus, zoomMinus, xRotation, yRotation, zRotation, xTraslationP, yTraslationP, zTraslationP, xTraslationM, yTraslationM, zTraslationM;

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

        Matrix.setMatrix();

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

    private void onClickZRotation() {
        Matrix.Z_ROTATION += 0.1;
        this.repaint();
    }

    private void onClickYRotation() {
        Matrix.Y_ROTATION += 0.1;
        this.repaint();
    }

    private void onClickXRotation() {
        Matrix.X_ROTATION += 0.1;
        this.repaint();
    }

    private void onClickZoomMinus() {
        Matrix.X_ZOOM -= 0.1;
        Matrix.Y_ZOOM -= 0.1;
        Matrix.Z_ZOOM -= 0.1;
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
