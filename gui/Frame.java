package cluedo.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
import cluedo.tile.Tile;



public class Frame extends JFrame implements KeyListener, MouseListener{

	private static final long serialVersionUID = 8535747294298615874L;
	private JPanel outerMostPanel; // panel that contains all other components
	private JMenuBar menuBar; // menu bar
	private JMenu file; // File on menu
	private JMenuItem menuNewGame; // new game under file 
	private JMenuItem menuExit; // exit under file
	private BoardPanel board; // displays the board
	private OptionsPanel options; // panel that displays the options available to the player and also the text area
	private HandPanel hand; // panel that displays the current players hand
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
		addMouseListener(this);
		setFocusable(true);
		requestFocus();
	}
	
	/**
	 * update the GUI with a players new position
	 * 
	 * @param player - player that moved
	 * @param oldPos - old position of the player
	 * @param newPos - new position of the player
	 */
	public void movePlayer(Player player, Position oldPos, Position newPos) {
		board.movePlayer(player, oldPos, newPos);
	}
	
	
	public void buttonPressed(ActionEvent e){
		String s = e.getActionCommand();
		System.out.println(s);
		switch(s){
		case "Roll Dice":
			game.diceRoll();
			break;
		case "Guess":
			//if(game.getCurrentPlayer().getRoom() == null){break;}
			String[] guess = createGuessAccuseGUI(true);
			if(guess == null){break;}
			game.guessAccuse(guess[0], null, guess[1], false);
			break;
		case "Accuse":	
			String[] accuse = createGuessAccuseGUI(true);
			if(accuse == null){break;}
			game.guessAccuse(accuse[0], accuse[1], accuse[2], true);
			break;
		case "End Turn":
			endTurn();
			break;
		case "Use Stairs":
			Stairs stairMove = new Stairs(game, game.getCurrentPlayer());
			if(stairMove.isValid()){
				stairMove.run();
			}
		}
		requestFocus();
	}
	
	
	/**
	 * creates the GUI for a guess or an accuse and returns the selected options for them.
	 * 
	 * @param isAccuse - is this an accuse for a guess
	 * @return string array holding the selected options (character-> room -> weapon)
	 */
	private String[] createGuessAccuseGUI(boolean isAccuse) {
		String[] answers;
		//create correct length array
		if(isAccuse){answers = new String[3];} 
		else {answers = new String[2];} // a guess does not need to have a room
		
		//create character select GUI
		CharacterSelect cs = new CharacterSelect();
		int i = JOptionPane.showOptionDialog(this, cs, "Character Select", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if(i == JOptionPane.CANCEL_OPTION || i == JOptionPane.CLOSED_OPTION) {
			return null;
		}
		answers[0] = cs.getSelectedChar();
		//if is an accuse get a room
		if(isAccuse){
			// create Room select GUI
			RoomSelect rs = new RoomSelect(game);
			int k = JOptionPane.showOptionDialog(this, rs, "Room Select", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if(k == JOptionPane.CANCEL_OPTION || k == JOptionPane.CLOSED_OPTION) {
				return null;
			}
			answers[1] = rs.getSelectedRoom();
		}
		// create weapon select GUI
		WeaponSelect ws = new WeaponSelect(game);
		int j = JOptionPane.showOptionDialog(this, ws, "Weapon Select", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if(j == JOptionPane.CANCEL_OPTION || j == JOptionPane.CLOSED_OPTION) {
			return null;
		}
		answers[answers.length-1] = ws.getSelectedWeapon();
		return answers;
	}

	
	/**
	 * the game has finished
	 * 
	 * @param player - player that won
	 */
	public void gameOver(Player player) {
		int option = JOptionPane.showOptionDialog(this,player.getName()+" has Won!", "Winner!",JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null,new String[]{"New Game","Exit"},"New Game");
		if(option == JOptionPane.OK_OPTION){newGame();}
		else {System.exit(0);}
	}
	

	/**
	 * create a new game
	 */
	private void newGame() {
		// create new game here
	}
	
	/**
	 * end the current turn
	 */
	public void endTurn() {
		game.nextPlayer();
		options.rollEnabled(true);
		if(game.hasWon()){gameOver(game.getCurrentPlayer());}
		hand.updateLabels();
		Output.setText("Player "+game.getCurrentPlayer().getName()+"'s turn\n");
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
	}
	@Override
	public void keyTyped(KeyEvent arg0) {	
	}

	public OptionsPanel getOptions() {
		return options;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		requestFocus();
		int x = e.getX()-board.getX()-8;
		int y = e.getY()-board.getY()-53;
		Tile tile = board.checkMouseOnDoor(x, y);
		if(tile!=null){	game.doorClicked(tile);}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}









}
