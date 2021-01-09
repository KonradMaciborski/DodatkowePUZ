package com.company;

import java.awt.Color;
import java.time.Duration;
import java.time.Instant;

import javax.swing.*;

public class MyThread extends Thread {

	Pole[][] fields;

	JFrame frame;
	JPanel gridPanel;

	JButton button;
	JButton kotyButton;
	JButton myszyButton;

	Color cat;
	Color mouse;
	Color catBorder;
	Color mouseBorder;

	int size;
	boolean running;
	
	public MyThread(int size, Pole[][] fields, JPanel gridPanel, JFrame frame,
							JButton button, JButton myszyButton, JButton kotyButton) {

		this.running = true;

		this.size = size;
		this.frame = frame;
		this.fields = fields;
		this.button = button;
		this.gridPanel = gridPanel;
		this.kotyButton = kotyButton;
		this.myszyButton = myszyButton;

		this.cat = Color.RED;
		this.mouse = Color.GREEN;
		this.catBorder = new Color(255, 196, 196);
		this.mouseBorder = new Color(195, 253, 195);

	}

	@Override
	public void run() {

		final Instant start = Instant.now();

		button.setEnabled(false);

		while (running) {

			Pole[][] tmp = new Pole[size][size];

			for (int i = 0; i < tmp.length; i++) {
				for (int j = 0; j < tmp.length; j++) {
					tmp[i][j] = new Pole(myszyButton, kotyButton);
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
						else if (fields[(i - 1 + size) % size][(j + size) % size].getColor().equals(cat) ||
								fields[(i - 1 + size) % size][(j + size) % size].getColor().equals(catBorder)) {
							neighbors++;
						}
						else if (fields[(i - 1 + size) % size][(j + 1 + size) % size].getColor().equals(cat) ||
								fields[(i - 1 + size) % size][(j + 1 + size) % size].getColor().equals(catBorder)) {
							neighbors++;
						}
						else if (fields[(i + size) % size][(j - 1 + size) % size].getColor().equals(cat) ||
								fields[(i + size) % size][(j - 1 + size) % size].getColor().equals(catBorder)) {
							neighbors++;
						}
						else if (fields[(i + size) % size][(j + 1 + size) % size].getColor().equals(cat) ||
								fields[(i + size) % size][(j + 1 + size) % size].getColor().equals(catBorder)) {
							neighbors++;
						}
						else if (fields[(i + 1 + size) % size][(j + 1 + size) % size].getColor().equals(cat) ||
								fields[(i + 1 + size) % size][(j + 1 + size) % size].getColor().equals(catBorder)) {
							neighbors++;
						}
						else if (fields[(i + 1 + size) % size][(j + size) % size].getColor().equals(cat) ||
								fields[(i + 1 + size) % size][(j + size) % size].getColor().equals(catBorder)) {
							neighbors++;
						}
						else if (fields[(i + 1 + size) % size][(j - 1 + size) % size].getColor().equals(cat) ||
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
				Thread.sleep(250);
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

			if(!czyJeszczeSaMyszy) {

				final Instant end = Instant.now();
				final Duration interval = Duration.between(start, end);

				JOptionPane.showMessageDialog(frame, "Koniec! Symulacja trwała: " + interval.getSeconds() + " sekund");

				running = false;
				//System.exit(0); //XD
			}

		}
	}
}
