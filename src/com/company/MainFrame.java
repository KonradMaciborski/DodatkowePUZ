package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

    private MyThread thread;

    private int size;
    private Pole[][] fields;

    private JPanel gridPanel;
    private JPanel buttonPanel;

    private JButton button;
    private JButton kotyButton;
    private JButton myszyButton;


    public MainFrame() {

        size = 50;
        fields = new Pole[size][size];

        this.setTitle("K.Maciborski s16013 - PUZ Dodatkowe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(size, size));

        button = new JButton("START");
        kotyButton = new JButton("Koty");
        myszyButton = new JButton("Myszy");

        buttonPanel = new JPanel();
        buttonPanel.add(button);
        buttonPanel.add(kotyButton);
        buttonPanel.add(myszyButton);

        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                fields[i][j] = new Pole(myszyButton, kotyButton);
                gridPanel.add(fields[i][j]);
            }
        }

        this.getContentPane().add(gridPanel, BorderLayout.CENTER);
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        this.repaint();
        gridPanel.repaint();

        this.setVisible(true);

        kotyButton.addActionListener(e -> {
            kotyButton.setEnabled(false);
            myszyButton.setEnabled(true);
            kotyButton.setBackground(Color.CYAN);
            myszyButton.setBackground(null);
        });

        myszyButton.addActionListener(e -> {
            myszyButton.setEnabled(false);
            kotyButton.setEnabled(true);
            myszyButton.setBackground(Color.CYAN);
            kotyButton.setBackground(null);
        });

        button.addActionListener(e -> {
            myszyButton.setEnabled(false);
            kotyButton.setEnabled(false);
            myszyButton.setBackground(null);
            kotyButton.setBackground(null);
            startThread();
        });
    }

    private void startThread() {

        thread = new MyThread(size, fields, gridPanel, this, button, myszyButton, kotyButton);
        thread.start();

    }

}
