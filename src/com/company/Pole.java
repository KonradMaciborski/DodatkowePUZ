package com.company;

import java.awt.Color;

import javax.swing.JButton;


public class Pole extends JButton {

	Color color;
	Color catColor;
	Color mouseColor;

	boolean areKotyEnabled;
	boolean areMyszyEnabled;

	public Pole(JButton mouseButton, JButton catButton) {

		this.color = Color.WHITE;
		this.catColor = Color.RED;
		this.mouseColor = Color.GREEN;

		this.setText(null);
		this.setBackground(color);
		this.setBounds(10, 10, 1, 1);

		this.addActionListener(event -> {

			System.out.println("Myszy:" + areMyszyEnabled + ", Koty: " + areKotyEnabled);

			if(!mouseButton.isEnabled()){

				if (!color.equals(mouseColor)) {
					color = mouseColor;
					this.setBackground(color);
				} else {
					color = Color.WHITE;
					this.setBackground(color);
				}
			}
			else if (!catButton.isEnabled()) {

				if (!color.equals(catColor)) {
					color = catColor;
					this.setBackground(color);
				} else {
					color = Color.WHITE;
					this.setBackground(color);
				}
			}
		});
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		this.setBackground(color);
		this.repaint();
	}

}
