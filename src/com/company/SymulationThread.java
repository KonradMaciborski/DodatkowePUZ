package com.company;

import java.awt.Color;
import java.time.Duration;
import java.time.Instant;

import javax.swing.*;

public class SymulationThread extends Thread {

	private int size;
	private boolean running;

	private Pole[][] fields;

	private MainFrame mainFrame;

	private JPanel gridPanel;

	private JButton button;
	private JButton kotyButton;
	private JButton myszyButton;

	private Color cat;
	private Color mouse;
	private Color catBorder;
	private Color mouseBorder;


	public SymulationThread(MainFrame mainFrame) {

		this.running = true;

		this.mainFrame = mainFrame;
		this.mainFrame.setLocationRelativeTo(null);

		this.size = mainFrame.getfSize();
		this.fields = mainFrame.getFields();
		this.button = mainFrame.getStartButton();
		this.gridPanel = mainFrame.getGridPanel();
		this.kotyButton = mainFrame.getKotyButton();
		this.myszyButton = mainFrame.getMyszyButton();

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

						if (fields[(i - 1 + size) % size][j].getColor().equals(Color.WHITE))
							fields[(i - 1 + size) % size][j].setColor(mouseBorder);

						if (fields[(i - 1 + size) % size][(j + 1 + size) % size].getColor().equals(Color.WHITE))
							fields[(i - 1 + size) % size][(j + 1 + size) % size].setColor(mouseBorder);

						if (fields[i][(j - 1 + size) % size].getColor().equals(Color.WHITE))
							fields[i][(j - 1 + size) % size].setColor(mouseBorder);

						if (fields[i][(j + 1 + size) % size].getColor().equals(Color.WHITE))
							fields[i][(j + 1 + size) % size].setColor(mouseBorder);

						if (fields[(i + 1 + size) % size][(j + 1 + size) % size].getColor().equals(Color.WHITE))
							fields[(i + 1 + size) % size][(j + 1 + size) % size].setColor(mouseBorder);

						if (fields[(i + 1 + size) % size][j].getColor().equals(Color.WHITE))
							fields[(i + 1 + size) % size][j].setColor(mouseBorder);

						if (fields[(i + 1 + size) % size][(j - 1 + size) % size].getColor().equals(Color.WHITE))
							fields[(i + 1 + size) % size][(j - 1 + size) % size].setColor(mouseBorder);
					}

					if (fields[i][j].getColor().equals(cat)) {

						if (!fields[(i - 1 + size) % size][(j - 1 + size) % size].getColor().equals(cat))
							fields[(i - 1 + size) % size][(j - 1 + size) % size].setColor(catBorder);

						if (!fields[(i - 1 + size) % size][j].getColor().equals(cat))
							fields[(i - 1 + size) % size][j].setColor(catBorder);

						if (!fields[(i - 1 + size) % size][(j + 1 + size) % size].getColor().equals(cat))
							fields[(i - 1 + size) % size][(j + 1 + size) % size].setColor(catBorder);

						if (!fields[i][(j - 1 + size) % size].getColor().equals(cat))
							fields[i][(j - 1 + size) % size].setColor(catBorder);

						if (!fields[i][(j + 1 + size) % size].getColor().equals(cat))
							fields[i][(j + 1 + size) % size].setColor(catBorder);

						if (!fields[(i + 1 + size) % size][(j + 1 + size) % size].getColor().equals(cat))
							fields[(i + 1 + size) % size][(j + 1 + size) % size].setColor(catBorder);

						if (!fields[(i + 1 + size) % size][j].getColor().equals(cat))
							fields[(i + 1 + size) % size][j].setColor(catBorder);

						if (!fields[(i + 1 + size) % size][(j - 1 + size) % size].getColor().equals(cat))
							fields[(i + 1 + size) % size][(j - 1 + size) % size].setColor(catBorder);
					}

					int neighbors = 0;
					if (fields[i][j].getColor().equals(mouse)) {

						if (fields[(i - 1 + size) % size][(j - 1 + size) % size].getColor().equals(cat) ||
								fields[(i - 1 + size) % size][(j - 1 + size) % size].getColor().equals(catBorder)) {
							neighbors++;
						}
						else if (fields[(i - 1 + size) % size][j].getColor().equals(cat) ||
								fields[(i - 1 + size) % size][j].getColor().equals(catBorder)) {
							neighbors++;
						}
						else if (fields[(i - 1 + size) % size][(j + 1 + size) % size].getColor().equals(cat) ||
								fields[(i - 1 + size) % size][(j + 1 + size) % size].getColor().equals(catBorder)) {
							neighbors++;
						}
						else if (fields[i][(j - 1 + size) % size].getColor().equals(cat) ||
								fields[i][(j - 1 + size) % size].getColor().equals(catBorder)) {
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
						else if (fields[(i + 1 + size) % size][j].getColor().equals(cat) ||
								fields[(i + 1 + size) % size][j].getColor().equals(catBorder)) {
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

						Color animalColorTmp = fields[i][j].getColor();
						int counter = 0;

						int iMove = 0;
						int jMove = 0;

						do {

							iMove = Math.random() > 0.5 ? 1 : -1;
							jMove = Math.random() > 0.5 ? 1 : -1;
							counter++;

						} while(tmp[(i + iMove + size) % size][(j + jMove + size) % size].getColor() == animalColorTmp && counter < 8);

						if (counter < 8) {
							tmp[(i + iMove + size) % size][(j + jMove + size) % size].setColor(fields[i][j].getColor());
							tmp[i][j].setColor(Color.WHITE);
						}

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
			mainFrame.repaint();

			if(!czyJeszczeSaMyszy) {

				final Instant end = Instant.now();
				final Duration interval = Duration.between(start, end);

				JOptionPane.showMessageDialog(mainFrame, "Koniec! Symulacja trwała: " + interval.getSeconds() + " sekund");

				running = false;

				System.exit(0); //XD
			}

		}
	}
}
