package com.company;

import java.awt.Color;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Pole extends JButton {

	Color color;

	public Pole() {

		this.color = Color.WHITE;
		this.setBackground(color);
		this.setText(null);
		this.setBounds(10, 10, 1, 1);


		this.addActionListener(e -> {

			if(!Main.myszyButton.isEnabled()){

				if (!color.equals(Color.GREEN)) {
					color = Color.GREEN;
					this.setBackground(color);
				} else {
					color = Color.WHITE;
					this.setBackground(color);
				}

			} else if (!Main.kotyButton.isEnabled()){

				if (!color.equals(Color.RED)) {
					color = Color.RED;
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
