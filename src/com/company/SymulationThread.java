package com.company;

import java.awt.Color;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class SymulationThread extends Thread {

	private int fSize;
	private boolean running;

	private Tile[][] fields;

	private MainFrame mainFrame;

	private JPanel gridPanel;

	private JButton startButton;
	private JButton catButton;
	private JButton mouseButton;

	private Color cat;
	private Color mouse;
	private Color catBorder;
	private Color mouseBorder;


	public SymulationThread(MainFrame mainFrame) {

		this.running = true;

		this.mainFrame = mainFrame;
		this.mainFrame.setLocationRelativeTo(null);

		this.fSize = mainFrame.getfSize();
		this.fields = mainFrame.getFields();
		this.startButton = mainFrame.getStartButton();
		this.gridPanel = mainFrame.getGridPanel();
		this.catButton = mainFrame.getCatButton();
		this.mouseButton = mainFrame.getMouseButton();

		this.cat = Color.RED;
		this.mouse = Color.GREEN;
		this.catBorder = new Color(255, 196, 196);
		this.mouseBorder = new Color(195, 253, 195);

	}

	@Override
	public void run() {

		final Instant start = Instant.now();

		Tile[][] tmp = new Tile[fSize][fSize];
		startButton.setEnabled(false);

		while (running) {

			for (int i = 0; i < fSize; i++) {
				for (int j = 0; j < fSize; j++) {

					tmp[i][j] = new Tile(mouseButton, catButton);

					if (fields[i][j].getColor().equals(mouse)) {
						setBorderForMouse(i, j);
					}

					if (fields[i][j].getColor().equals(cat)) {
						setBorderForCat(i, j);
					}

				}
			}

			for (int i = 0; i < fields.length; i++) {
				for (int j = 0; j < fields[i].length; j++) {

					if (fields[i][j].getColor().equals(mouse)) {
						if (getAmountOfCatNeighborsForMouse(i, j) > 0) {
							eat(i, j);
						}
					}
				}
			}

			gridPanel.repaint();

			for (int i = 0; i < fields.length; i++) {
				for (int j = 0; j < fields[i].length; j++) {

					if(fields[i][j].getColor().equals(cat) || fields[i][j].getColor().equals(mouse)) {

						Color animalColorTmp = fields[i][j].getColor();

						int iMove;
						int jMove;
						int counter = 0;

						do {

							iMove = Math.random() > 0.5 ? 1 : -1;
							jMove = Math.random() > 0.5 ? 1 : -1;
							counter++;

						} while(tmp[(i + iMove + fSize) % fSize][(j + jMove + fSize) % fSize].getColor() == animalColorTmp && counter < 8);

						if (counter < 8) {
							tmp[(i + iMove + fSize) % fSize][(j + jMove + fSize) % fSize].setColor(animalColorTmp);
							tmp[i][j].setColor(Color.WHITE);
						} else {
							tmp[i][j].setColor(animalColorTmp);
						}

					}
				}
			}

			gridPanel.repaint();

			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			gridPanel.removeAll();

			boolean czyJeszczeSaMyszy = false;

			for (int i = 0; i < fSize; i++) {
				for (int j = 0; j < fSize; j++) {

					fields[i][j] = tmp[i][j];

					gridPanel.add(fields[i][j]);

					if(fields[i][j].getColor().equals(mouse)){
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

	private void setBorderForMouse(int i, int j){

		if (fields[(i - 1 + fSize) % fSize][(j - 1 + fSize) % fSize].getColor().equals(Color.WHITE))
			fields[(i - 1 + fSize) % fSize][(j - 1 + fSize) % fSize].setColor(mouseBorder);

		if (fields[(i - 1 + fSize) % fSize][j].getColor().equals(Color.WHITE))
			fields[(i - 1 + fSize) % fSize][j].setColor(mouseBorder);

		if (fields[(i - 1 + fSize) % fSize][(j + 1 + fSize) % fSize].getColor().equals(Color.WHITE))
			fields[(i - 1 + fSize) % fSize][(j + 1 + fSize) % fSize].setColor(mouseBorder);

		if (fields[i][(j - 1 + fSize) % fSize].getColor().equals(Color.WHITE))
			fields[i][(j - 1 + fSize) % fSize].setColor(mouseBorder);

		if (fields[i][(j + 1 + fSize) % fSize].getColor().equals(Color.WHITE))
			fields[i][(j + 1 + fSize) % fSize].setColor(mouseBorder);

		if (fields[(i + 1 + fSize) % fSize][(j + 1 + fSize) % fSize].getColor().equals(Color.WHITE))
			fields[(i + 1 + fSize) % fSize][(j + 1 + fSize) % fSize].setColor(mouseBorder);

		if (fields[(i + 1 + fSize) % fSize][j].getColor().equals(Color.WHITE))
			fields[(i + 1 + fSize) % fSize][j].setColor(mouseBorder);

		if (fields[(i + 1 + fSize) % fSize][(j - 1 + fSize) % fSize].getColor().equals(Color.WHITE))
			fields[(i + 1 + fSize) % fSize][(j - 1 + fSize) % fSize].setColor(mouseBorder);

	}

	private void setBorderForCat(int i, int j){

		processCatBorderTile(i, (j - 1 + fSize) % fSize);
		processCatBorderTile(i, (j + 1 + fSize) % fSize);

		processCatBorderTile((i + 1 + fSize) % fSize, j);
		processCatBorderTile((i - 1 + fSize) % fSize, j);

		processCatBorderTile((i + 1 + fSize) % fSize, (j + 1 + fSize) % fSize);
		processCatBorderTile((i + 1 + fSize) % fSize, (j - 1 + fSize) % fSize);

		processCatBorderTile((i - 1 + fSize) % fSize, (j - 1 + fSize) % fSize);
		processCatBorderTile((i - 1 + fSize) % fSize, (j + 1 + fSize) % fSize);

	}

	private void processCatBorderTile(int i, int j){

		if (!fields[i][j].getColor().equals(cat)) {
			if (fields[i][j].getColor().equals(mouse)) {
				eat(i, j);
			}
			fields[i][j].setColor(catBorder);
		}

	}

	private int getAmountOfCatNeighborsForMouse(int i, int j){

		int neighbors = 0;

		if (fields[(i - 1 + fSize) % fSize][(j - 1 + fSize) % fSize].getColor().equals(cat) ||
				fields[(i - 1 + fSize) % fSize][(j - 1 + fSize) % fSize].getColor().equals(catBorder)) {
			neighbors++;
		}
		else if (fields[(i - 1 + fSize) % fSize][j].getColor().equals(cat) ||
				fields[(i - 1 + fSize) % fSize][j].getColor().equals(catBorder)) {
			neighbors++;
		}
		else if (fields[(i - 1 + fSize) % fSize][(j + 1 + fSize) % fSize].getColor().equals(cat) ||
				fields[(i - 1 + fSize) % fSize][(j + 1 + fSize) % fSize].getColor().equals(catBorder)) {
			neighbors++;
		}
		else if (fields[i][(j - 1 + fSize) % fSize].getColor().equals(cat) ||
				fields[i][(j - 1 + fSize) % fSize].getColor().equals(catBorder)) {
			neighbors++;
		}
		else if (fields[i][(j + 1 + fSize) % fSize].getColor().equals(cat) ||
				fields[i][(j + 1 + fSize) % fSize].getColor().equals(catBorder)) {
			neighbors++;
		}
		else if (fields[(i + 1 + fSize) % fSize][(j + 1 + fSize) % fSize].getColor().equals(cat) ||
				fields[(i + 1 + fSize) % fSize][(j + 1 + fSize) % fSize].getColor().equals(catBorder)) {
			neighbors++;
		}
		else if (fields[(i + 1 + fSize) % fSize][j].getColor().equals(cat) ||
				fields[(i + 1 + fSize) % fSize][j].getColor().equals(catBorder)) {
			neighbors++;
		}
		else if (fields[(i + 1 + fSize) % fSize][(j - 1 + fSize) % fSize].getColor().equals(cat) ||
				fields[(i + 1 + fSize) % fSize][(j - 1 + fSize) % fSize].getColor().equals(catBorder)) {
			neighbors++;
		}

		return neighbors;

	}

	private void eat(int i, int j) {

		Color actualColor = fields[i][j].getColor();

		for (int x = 0; x < 11; x++) {

				if (!fields[i][j].getColor().equals(Color.WHITE)) {
					fields[i][j].setColor(Color.WHITE);
				} else {
					fields[i][j].setColor(Color.BLACK);
				}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			gridPanel.repaint();

		}

		if (!actualColor.equals(cat) || !actualColor.equals(catBorder)) {
			fields[i][j].setColor(Color.WHITE);
		} else {
			fields[i][j].setColor(actualColor);
		}
		gridPanel.repaint();
	}

}
