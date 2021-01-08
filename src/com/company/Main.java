package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	static int size = 50;
	static Pole[][] fields = new Pole[size][size];

	static JPanel gridPanel;
	static JFrame frame;
	static JButton button;
	static MyThread thread;
	static JButton stopButton;
	static JButton resetButton;

	static JButton kotyButton;
	static JButton myszyButton;



	public static void main(String[] args) {

		frame = new JFrame("Gra w życie");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		gridPanel = new JPanel();

		gridPanel.setLayout(new GridLayout(size, size));

		for (int i = 0; i < fields.length; i++) {
			for (int j = 0; j < fields.length; j++) {
				fields[i][j] = new Pole();
				gridPanel.add(fields[i][j]);
			}
		}

		JPanel buttonPanel = new JPanel();

		stopButton = new JButton("STOP");
		button = new JButton("START");
		resetButton = new JButton("RESET");

		kotyButton = new JButton("Koty");
		myszyButton = new JButton("Myszy");

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

		resetButton.addActionListener(e -> {
			resetGrid();
		});
		stopButton.setEnabled(false);
		stopButton.addActionListener(e -> {
			stopThread();
		});

		button.addActionListener(e -> {

			myszyButton.setEnabled(false);
			kotyButton.setEnabled(false);
			myszyButton.setBackground(null);
			kotyButton.setBackground(null);

			startThread();
		});

		buttonPanel.add(button);
		buttonPanel.add(kotyButton);
		buttonPanel.add(myszyButton);

		frame.getContentPane().add(gridPanel, BorderLayout.CENTER);
		frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		frame.repaint();
		gridPanel.repaint();
		frame.setVisible(true);

	}

	private static void resetGrid() {
		resetButton.setEnabled(false);
		button.setEnabled(false);
		try {
			if (thread.isRunning())
				thread.zamknijSei();
		} catch (Exception e) {

		}
		for (int i = 0; i < fields.length; i++) {
			for (int j = 0; j < fields.length; j++) {
				if (fields[i][j].getColor().equals(Color.BLACK))
					fields[i][j].setColor(Color.WHITE);
			}
		}
		button.setEnabled(true);
		resetButton.setEnabled(true);
	}

	private static void stopThread() {
		try {
			thread.zamknijSei();
		} catch (Exception e) {

		}
		button.setEnabled(true);
		stopButton.setEnabled(false);
	}

	private static void startThread() {
		thread = new MyThread(stopButton, button, size, fields, gridPanel, frame);
		thread.start();
	}

}
