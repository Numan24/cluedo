package cluedo.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cluedo.Game;


public class Frame extends JFrame implements KeyListener{

	private JPanel outerMostPanel;
	private JMenuBar menuBar;
	private JMenu file;
	private JMenuItem menuNewGame;
	private JMenuItem menuExit;
	private JPanel board;
	private OptionsPanel options;
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
		options = new OptionsPanel(this, game);
		outerMostPanel.add(options, BorderLayout.WEST);
		
		//create hand panel (contains the current players hand)
		hand = new HandPanel(game);
		outerMostPanel.add(hand, BorderLayout.SOUTH);
		
		pack();
		this.setLocationRelativeTo(null);
		
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
	}

	public void buttonPressed(ActionEvent e){
		String s = e.getActionCommand();
		System.out.println(s);
		switch(s){
		case "Roll Dice":
			game.diceRoll();
			
			break;
		case "Guess":		
			break;
		case "Accuse":		
			break;
		case "End Turn":	
			game.setCurrentPlayer(game.nextPlayer());
			break;
		}
		requestFocus();
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		char c = e.getKeyChar();
		switch(c){
			case 'w': 
				game.moveDetected("N");
				break;
			case 'a':
				game.moveDetected("W");
				break;
			case 's':
				game.moveDetected("S");
				break;
			case 'd':
				game.moveDetected("E");
				break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
