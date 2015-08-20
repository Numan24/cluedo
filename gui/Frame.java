package cluedo.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cluedo.Game;
import cluedo.Player;


public class Frame extends JFrame {

	private JPanel outerMostPanel;
	private JMenuBar menuBar;
	private JMenu file;
	private JMenuItem menuNewGame;
	private JMenuItem menuExit;
	private JPanel board;
	private JPanel options;
	private JPanel hand;
	
	private Game game;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		//create and setup game
		this.game = new Game();
		
		//set close operation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//create menu bar
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// create file in menu bar
		file = new JMenu("File");
		menuBar.add(file);
		
		//add new game to file
		menuNewGame = new JMenuItem("New Game");
		file.add(menuNewGame);
		
		// add exit to file
		menuExit = new JMenuItem("Exit");
		file.add(menuExit);
		
		// create outer most panel
		outerMostPanel = new JPanel();
		outerMostPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		outerMostPanel.setLayout(new BorderLayout(0, 0));
		
		//add panel to frame
		setContentPane(outerMostPanel);
		
		//create board panel (contains the board grid)
		board = new boardPanel(game);
		outerMostPanel.add(board, BorderLayout.EAST);
		
		//create options panel (contains the option buttons and text area)
		options = new OptionsPanel(game);
		outerMostPanel.add(options, BorderLayout.WEST);
		
		//create hand panel (contains the current players hand)
		hand = new HandPanel(game);
		outerMostPanel.add(hand, BorderLayout.SOUTH);
		
		pack();
		this.setLocationRelativeTo(null);
	}
	
	

}
