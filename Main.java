package cluedo;

import java.awt.EventQueue;

import cluedo.gui.Frame;

/**
 * the main class for running the game
 *
 */
public class Main {

	static boolean gameFinished = false;
	static private Frame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Frame();
					Output.setFrame(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public static void restart(){
		frame = new Frame();
		Output.setFrame(frame);
		frame.setVisible(true);
	}





}
