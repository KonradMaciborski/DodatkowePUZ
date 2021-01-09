package com.company;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		MainFrame mainFrame = new MainFrame(50);

		while (mainFrame.getStartButton().isEnabled()) Thread.sleep(10);

		Thread st = new SymulationThread(mainFrame);
		st.start();

	}

}
