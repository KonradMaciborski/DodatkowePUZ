package com.company;

import java.awt.Color;
import java.time.Duration;
import java.time.Instant;

import javax.swing.*;

public class MyThread extends Thread {

	boolean running = true;
	JButton stopButton;
	JButton button;
	int size;
	
	Pole[][]fields;
	
	JPanel gridPanel;
	JFrame frame;

	Color cat = Color.RED;
	Color catBorder = new Color(255, 196, 196);
	Color mouse = Color.GREEN;
	Color mouseBorder = new Color(195, 253, 195);

	
	public MyThread(JButton stopButton, JButton button, int size, Pole[][] fields, JPanel gridPanel, JFrame frame) {
		super();
		this.stopButton = stopButton;
		this.button = button;
		this.size = size;
		this.fields = fields;
		this.gridPanel = gridPanel;
		this.frame = frame;
	}

	public void zamknijSei(){
		running = false;
	}
	
	public boolean isRunning(){
		return running;
	}

	public void run() {

		final Instant start = Instant.now();

		stopButton.setEnabled(true);
		button.setEnabled(false);

		while (running) {

			Pole[][] tmp = new Pole[size][size];

			for (int i = 0; i < tmp.length; i++) {
				for (int j = 0; j < tmp.length; j++) {
					tmp[i][j] = new Pole();
				}
			}

			for (int i = 0; i < fields.length; i++) {
				for (int j = 0; j < fields[i].length; j++) {

					if (fields[i][j].getColor().equals(mouse)) {

						if (fields[(i - 1 + size) % size][(j - 1 + size) % size].getColor().equals(Color.WHITE))
							fields[(i - 1 + size) % size][(j - 1 + size) % size].setColor(mouseBorder);

						if (fields[(i - 1 + size) % size][(j + size) % size].getColor().equals(Color.WHITE))
							fields[(i - 1 + size) % size][(j + size) % size].setColor(mouseBorder);

						if (fields[(i - 1 + size) % size][(j + 1 + size) % size].getColor().equals(Color.WHITE))
							fields[(i - 1 + size) % size][(j + 1 + size) % size].setColor(mouseBorder);

						if (fields[(i + size) % size][(j - 1 + size) % size].getColor().equals(Color.WHITE))
							fields[(i + size) % size][(j - 1 + size) % size].setColor(mouseBorder);

						if (fields[(i + size) % size][(j + 1 + size) % size].getColor().equals(Color.WHITE))
							fields[(i + size) % size][(j + 1 + size) % size].setColor(mouseBorder);

						if (fields[(i + 1 + size) % size][(j + 1 + size) % size].getColor().equals(Color.WHITE))
							fields[(i + 1 + size) % size][(j + 1 + size) % size].setColor(mouseBorder);

						if (fields[(i + 1 + size) % size][(j + size) % size].getColor().equals(Color.WHITE))
							fields[(i + 1 + size) % size][(j + size) % size].setColor(mouseBorder);

						if (fields[(i + 1 + size) % size][(j - 1 + size) % size].getColor().equals(Color.WHITE))
							fields[(i + 1 + size) % size][(j - 1 + size) % size].setColor(mouseBorder);
					}

					if (fields[i][j].getColor().equals(cat)) {

						if (fields[(i - 1 + size) % size][(j - 1 + size) % size].getColor().equals(Color.WHITE))
							fields[(i - 1 + size) % size][(j - 1 + size) % size].setColor(catBorder);

						if (fields[(i - 1 + size) % size][(j + size) % size].getColor().equals(Color.WHITE))
							fields[(i - 1 + size) % size][(j + size) % size].setColor(catBorder);

						if (fields[(i - 1 + size) % size][(j + 1 + size) % size].getColor().equals(Color.WHITE))
							fields[(i - 1 + size) % size][(j + 1 + size) % size].setColor(catBorder);

						if (fields[(i + size) % size][(j - 1 + size) % size].getColor().equals(Color.WHITE))
							fields[(i + size) % size][(j - 1 + size) % size].setColor(catBorder);

						if (fields[(i + size) % size][(j + 1 + size) % size].getColor().equals(Color.WHITE))
							fields[(i + size) % size][(j + 1 + size) % size].setColor(catBorder);

						if (fields[(i + 1 + size) % size][(j + 1 + size) % size].getColor().equals(Color.WHITE))
							fields[(i + 1 + size) % size][(j + 1 + size) % size].setColor(catBorder);

						if (fields[(i + 1 + size) % size][(j + size) % size].getColor().equals(Color.WHITE))
							fields[(i + 1 + size) % size][(j + size) % size].setColor(catBorder);

						if (fields[(i + 1 + size) % size][(j - 1 + size) % size].getColor().equals(Color.WHITE))
							fields[(i + 1 + size) % size][(j - 1 + size) % size].setColor(catBorder);
					}

					int neighbors = 0;
					if (fields[i][j].getColor().equals(mouse)) {

						if (fields[(i - 1 + size) % size][(j - 1 + size) % size].getColor().equals(cat) ||
								fields[(i - 1 + size) % size][(j - 1 + size) % size].getColor().equals(catBorder)) {
							neighbors++;
						}

						if (fields[(i - 1 + size) % size][(j + size) % size].getColor().equals(cat) ||
								fields[(i - 1 + size) % size][(j + size) % size].getColor().equals(catBorder)) {
							neighbors++;
						}

						if (fields[(i - 1 + size) % size][(j + 1 + size) % size].getColor().equals(cat) ||
								fields[(i - 1 + size) % size][(j + 1 + size) % size].getColor().equals(catBorder)) {
							neighbors++;
						}

						if (fields[(i + size) % size][(j - 1 + size) % size].getColor().equals(cat) ||
								fields[(i + size) % size][(j - 1 + size) % size].getColor().equals(catBorder)) {
							neighbors++;
						}

						if (fields[(i + size) % size][(j + 1 + size) % size].getColor().equals(cat) ||
								fields[(i + size) % size][(j + 1 + size) % size].getColor().equals(catBorder)) {
							neighbors++;
						}

						if (fields[(i + 1 + size) % size][(j + 1 + size) % size].getColor().equals(cat) ||
								fields[(i + 1 + size) % size][(j + 1 + size) % size].getColor().equals(catBorder)) {
							neighbors++;
						}

						if (fields[(i + 1 + size) % size][(j + size) % size].getColor().equals(cat) ||
								fields[(i + 1 + size) % size][(j + size) % size].getColor().equals(catBorder)) {
							neighbors++;
						}

						if (fields[(i + 1 + size) % size][(j - 1 + size) % size].getColor().equals(cat) ||
								fields[(i + 1 + size) % size][(j - 1 + size) % size].getColor().equals(catBorder)) {
							neighbors++;
						}

						if (neighbors > 0) {
							fields[i][j].setColor(Color.WHITE);
						}
					}
				}
			}

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for (int i = 0; i < fields.length; i++) {
				for (int j = 0; j < fields[i].length; j++) {

					if(fields[i][j].getColor().equals(cat) || fields[i][j].getColor().equals(mouse)) {

						int iMove = Math.random() > 0.5 ? 1 : -1;
						int jMove = Math.random() > 0.5 ? 1 : -1;

						tmp[(i + iMove + size) % size][(j + jMove + size) % size].setColor(fields[i][j].getColor());
						tmp[i][j].setColor(Color.WHITE);

					}
				}
			}

			fields = tmp.clone();
			gridPanel.removeAll();

			boolean czyJeszczeSaMyszy = false;
			for (int i = 0; i < tmp.length; i++) {
				for (int j = 0; j < tmp.length; j++) {

					gridPanel.add(tmp[i][j]);

					if(tmp[i][j].getColor().equals(mouse)){
						czyJeszczeSaMyszy = true;
					}

				}
			}

			gridPanel.revalidate();
			gridPanel.repaint();
			frame.repaint();

			if(!czyJeszczeSaMyszy){

				final Instant end = Instant.now();
				final Duration interval = Duration.between(start, end);

				JOptionPane.showMessageDialog(frame, "Koniec! Symulacja trwała: " + interval.getSeconds() + " sekund");

				System.exit(0); //XD
			}

		}
	}
}
