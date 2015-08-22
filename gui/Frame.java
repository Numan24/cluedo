package cluedo.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cluedo.Game;
import cluedo.Output;
import cluedo.Player;
import cluedo.Position;
import cluedo.action.Stairs;



public class Frame extends JFrame implements KeyListener{

	private JPanel outerMostPanel;
	private JMenuBar menuBar;
	private JMenu file;
	private JMenuItem menuNewGame;
	private JMenuItem menuExit;
	private BoardPanel board;
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
					Output.setFrame(frame);
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
		this.game = new Game(this);
		
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
		board = new BoardPanel(game);
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
	
	
	public void movePlayer(Player player, Position oldPos, Position newPos) {
		board.movePlayer(player, oldPos, newPos);
	}
	
	public void enterRoom(Player currentPlayer) {
	}
	

	public void buttonPressed(ActionEvent e){
		String s = e.getActionCommand();
		System.out.println(s);
		switch(s){
		case "Roll Dice":
			game.diceRoll();
			
			break;
		case "Guess":
			if(game.getCurrentPlayer().getRoom() == null){break;}
			CharacterSelect cs = new CharacterSelect();
			int i = JOptionPane.showOptionDialog(this, cs, "Character Select", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if(i == JOptionPane.CANCEL_OPTION || i == JOptionPane.CLOSED_OPTION) {
				break;
			}
			String character = cs.getSelectedChar();
			WeaponSelect ws = new WeaponSelect(game);
			int j = JOptionPane.showOptionDialog(this, ws, "Weapon Select", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if(j == JOptionPane.CANCEL_OPTION || j == JOptionPane.CLOSED_OPTION) {
				break;
			}
			String weapon = ws.getSelectedWeapon();
			System.out.println(character);
			System.out.println(weapon);
			game.guess(character, weapon);
			break;
		case "Accuse":	
			break;
		case "End Turn":
			game.setCurrentPlayer(game.nextPlayer());
			Output.setText("Player "+game.getCurrentPlayer().getName()+"'s turn\n");
			break;
		case "Use Stairs":
			Stairs stairMove = new Stairs(game, game.getCurrentPlayer());
			if(stairMove.isValid()){
				stairMove.run();
			}
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
	
	public OptionsPanel getOptions() {
		return options;
	}



}
