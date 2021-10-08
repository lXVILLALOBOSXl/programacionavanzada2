package com.vistas;

import javax.swing.*;

public class GUI extends JFrame {
    private JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11;

    public GUI(){
        super();
        inicializa();
    }

    protected void inicializa(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("18100586");
        this.setLocationRelativeTo(null);

        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");
        b10 = new JButton("10");
        b11 = new JButton("11");

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
                                                    .addGroup(
                                                        groupLayout.createParallelGroup()
                                                                .addComponent(b1,100,100,200)
                                                                .addGroup(
                                                                    groupLayout.createSequentialGroup()
                                                                            .addComponent(b2,50,50,100)
                                                                            .addComponent(b3,50,50,100)
                                                                )

                                                    )
                                                    .addGroup(
                                                            groupLayout.createParallelGroup().addComponent(b4,30,30,40)
                                                    )
                                                    .addGroup(
                                                            groupLayout.createSequentialGroup()
                                                                    .addGroup(
                                                                            groupLayout.createParallelGroup()
                                                                                .addComponent(b5,100,100,200)
                                                                                .addComponent(b6,100,100,200)
                                                                    )
                                                    )
                                    )
                      )
                      .addComponent(b7,230,230,440)
                      .addGroup(
                              groupLayout.createParallelGroup()
                                      .addGroup(
                                              groupLayout.createSequentialGroup()
                                                      .addComponent(b8,55,55,100)
                                                      .addComponent(b9,60,60,110)
                                                      .addGroup(
                                                              groupLayout.createSequentialGroup()
                                                                      .addGroup(
                                                                              groupLayout.createParallelGroup()
                                                                                      .addComponent(b10,115,115,220)
                                                                                      .addComponent(b11,100,100,205)
                                                                      )
                                                      )
                                      )
                      )
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addGroup(
                                groupLayout.createSequentialGroup()
                                        .addGroup(
                                                groupLayout.createParallelGroup()
                                                        .addGroup(
                                                                groupLayout.createSequentialGroup()
                                                                        .addComponent(b1)
                                                                        .addGroup(
                                                                                groupLayout.createParallelGroup()
                                                                                        .addComponent(b2)
                                                                                        .addComponent(b3)
                                                                        )
                                                        )
                                                        .addGroup(
                                                                groupLayout.createSequentialGroup().addComponent(b4,65,65,65)
                                                        )
                                                        .addGroup(
                                                                groupLayout.createParallelGroup()
                                                                        .addGroup(
                                                                                groupLayout.createSequentialGroup()
                                                                                        .addComponent(b5)
                                                                                        .addComponent(b6)
                                                                        )
                                                        )
                                        )
                        )
                        .addComponent(b7)
                        .addGroup(
                                groupLayout.createSequentialGroup()
                                        .addGroup(
                                                groupLayout.createParallelGroup()
                                                        .addComponent(b8)
                                                        .addComponent(b9)
                                                        .addGroup(
                                                                groupLayout.createSequentialGroup()
                                                                        .addComponent(b10)
                                                                        .addComponent(b11)
                                                        )
                                        )
                        )
        );

        setLayout(groupLayout);
        this.pack();

    }

}
