package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

    private SymulationThread thread;

    private int fSize;
    private Pole[][] fields;

    private JPanel gridPanel;
    private JPanel buttonPanel;

    private JButton startButton;
    private JButton kotyButton;
    private JButton myszyButton;


    public MainFrame(int fSize) {

        this.fSize = fSize;
        this.fields = new Pole[fSize][fSize];

        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("K.Maciborski s16013 - PUZ Dodatkowe");

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(fSize, fSize));

        startButton = new JButton("START");
        kotyButton = new JButton("Koty");
        myszyButton = new JButton("Myszy");

        buttonPanel = new JPanel();
        buttonPanel.add(startButton);
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

        this.setLocationRelativeTo(null);
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

        startButton.addActionListener(e -> {
            myszyButton.setEnabled(false);
            kotyButton.setEnabled(false);
            myszyButton.setBackground(null);
            kotyButton.setBackground(null);
            startButton.setEnabled(false);
        });
    }

    public int getfSize() {
        return fSize;
    }

    public Pole[][] getFields() {
        return fields;
    }

    public JPanel getGridPanel() {
        return gridPanel;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getKotyButton() {
        return kotyButton;
    }

    public JButton getMyszyButton() {
        return myszyButton;
    }
}
