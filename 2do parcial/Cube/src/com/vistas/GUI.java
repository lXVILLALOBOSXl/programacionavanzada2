package com.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private JButton zoomPlus, zoomMinus, xRotation, yRotation, zRotation, xTraslation, yTraslation, zTraslation;
    private static Double [][] zoom = new Double[4][4];
    private static Double [][]  traslacion = new Double[4][4];
    private static Double [][] efecto = new Double[4][4];
    private static Double [][] x = new Double[4][4];
    private static Double [][] y = new Double[4][4];
    private static Double [][] z = new Double[4][4];
    private static Double [][] resultante = new Double[4][4];


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
        /*g.drawOval((this.getWidth()/2)-250,(this.getHeight()/2)-250,500,500)*/;
    }
}
