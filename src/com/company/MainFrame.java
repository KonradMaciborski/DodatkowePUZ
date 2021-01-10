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
    private Tile[][] fields;

    private JPanel gridPanel;
    private JPanel buttonPanel;

    private JButton startButton;
    private JButton catButton;
    private JButton mouseButton;


    public MainFrame(int fSize) {

        this.fSize = fSize;
        this.fields = new Tile[fSize][fSize];

        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("K.Maciborski s16013 - PUZ Dodatkowe");

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(fSize, fSize));

        startButton = new JButton("START");
        catButton = new JButton("Koty");
        mouseButton = new JButton("Myszy");

        buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(catButton);
        buttonPanel.add(mouseButton);

        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                fields[i][j] = new Tile(mouseButton, catButton);
                gridPanel.add(fields[i][j]);
            }
        }

        this.getContentPane().add(gridPanel, BorderLayout.CENTER);
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        this.setLocationRelativeTo(null);
        this.repaint();
        gridPanel.repaint();

        this.setVisible(true);

        catButton.addActionListener(e -> {
            catButton.setEnabled(false);
            mouseButton.setEnabled(true);
            catButton.setBackground(Color.CYAN);
            mouseButton.setBackground(null);
        });

        mouseButton.addActionListener(e -> {
            mouseButton.setEnabled(false);
            catButton.setEnabled(true);
            mouseButton.setBackground(Color.CYAN);
            catButton.setBackground(null);
        });

        startButton.addActionListener(e -> {
            mouseButton.setEnabled(false);
            catButton.setEnabled(false);
            mouseButton.setBackground(null);
            catButton.setBackground(null);
            startButton.setEnabled(false);
        });
    }

    public int getfSize() {
        return fSize;
    }

    public Tile[][] getFields() {
        return fields;
    }

    public JPanel getGridPanel() {
        return gridPanel;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getCatButton() {
        return catButton;
    }

    public JButton getMouseButton() {
        return mouseButton;
    }
}
