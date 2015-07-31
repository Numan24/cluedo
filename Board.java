package cluedo;

import java.util.*;

import cluedo.cards.Card;
import cluedo.cards.Character;
import cluedo.cards.Room;
import cluedo.cards.Weapon;

public class Board {

	private List<Card> win; // the cards 
	
	private List<Player> players; // players in the current game
	
	private List<Room> rooms = new ArrayList<Room>();
	private List<Weapon> weapons = new ArrayList<Weapon>();
	private List<Character> characters = new ArrayList<Character>();
	
	private int[][] board; // hard code board please
	private Player[][] playerPositions;
	
	private Player currentPlayer;

	
	/**
	 * Construct a board from a list of players
	 * @param players - list of players in the game
	 */
	public Board(List<Player> players) {
		this.players = players;
		currentPlayer = players.get(0);
		
		//weapons
		weapons.add(new Weapon("Dagger"));
		weapons.add(new Weapon("Revolver"));
		weapons.add(new Weapon("Lead Pipe"));
		weapons.add(new Weapon("Rope"));
		weapons.add(new Weapon("Spanner"));
		weapons.add(new Weapon("Candlestick"));
		//rooms
		rooms.add(new Room("Kitchen", null));
		rooms.add(new Room("Ball Room", null));
		rooms.add(new Room("Conservatory", null));
		rooms.add(new Room("Billiard Room", null));
		rooms.add(new Room("Library", null));
		rooms.add(new Room("Study", null));
		rooms.add(new Room("Hall", null));
		rooms.add(new Room("Lounge", null));
		rooms.add(new Room("Dining Room", null));
		
		rooms.get(0).setConnectedTo(rooms.get(5));
		rooms.get(5).setConnectedTo(rooms.get(0));
		rooms.get(2).setConnectedTo(rooms.get(7));
		rooms.get(7).setConnectedTo(rooms.get(2));
		
		//characters
		characters.add(new Character("Miss Scarlett"));
		characters.add(new Character("Colonel Mustard"));
		characters.add(new Character("Mrs. White"));
		characters.add(new Character("The Reverand Green"));
		characters.add(new Character("Mrs. Peacock"));
		characters.add(new Character("Professor Plum"));
	}
	
	
    /**
     * Shuffles cards randomly and distributes to all players as well as deciding the winning 3 cards.
     * 
     */
	public void distributeCards(){
		ArrayList<Card> allcards = new ArrayList<Card>();
		
		Collections.shuffle(rooms);
		Collections.shuffle(weapons);
		Collections.shuffle(characters);
		
		win.add(rooms.get(0));	
		win.add(weapons.get(0));
		win.add(characters.get(0));
		
		allcards.addAll(rooms.subList(1, rooms.size()-1));
		allcards.addAll(weapons.subList(1, weapons.size()-1));
		allcards.addAll(characters.subList(1, characters.size()-1));
		
		for(Card c: allcards){
			Player p = nextPlayer();
			p.addCard(c);
		}
	}
	
	/**
	 * takes two positions and moves player at old position to new position.
	 * @param oldPos & newPos - the positions to move player from.
	 */
	public void move(Position oldPos, Position newPos){
		Player p = playerPositions[oldPos.row()][oldPos.col()];
		playerPositions[newPos.row()][newPos.col()] = p;
		playerPositions[oldPos.row()][oldPos.col()] = null;
		p.move(newPos);
	}
	
	/**
	 * Check to see if a guess was correct.
	 * 
	 * @param guess - the cards that the player guessed.
	 * @return
	 */
	public boolean checkGuess(List<Card> guess) {
		return win.containsAll(guess);
	}
	
	/**
	 * Have turn for a player
	 * 
	 * @param player - player that is having a turn
	 * @return
	 */
	public String haveNextTurn(Player player) {
		//Dice rolling
		Random rand = new Random();
		int roll = rand.nextInt(5)+1;
		currentPlayer.setRoll(roll);
		System.out.println("You rolled a "+roll);
		
		String[] options = player.getOptions();
		Scanner input = new Scanner(System.in);
		String toPrint = "Please select an option: ";
		for(String option : options) {
			
			toPrint += "["+option+"] ";
		}
		System.out.println(toPrint);
		String option; 
		option = input.next();
		input.close();
		calculatePlay(option);
		return option;
		
	}

	
	public Player playerAt(Position p){
		return playerPositions[p.row()][p.col()];
	}
	
	
	/**
	 * Determine what happens for a given option.
	 * 
	 * @param option - the option selected by the player
	 */
	private void calculatePlay(String option) {
		Scanner sc = new Scanner(System.in);
		switch(option) {
		case "Move":
			// uhhhhh
			System.out.println("Choose new coords to move to 'x y'");
			Position toMoveTo = new Position(sc.nextInt(), sc.nextInt());
			while(!currentPlayer.isValidMove(toMoveTo)){
				System.out.println("Invalid Move.");
				System.out.println("Choose new coords to move to 'x y'");
				toMoveTo = new Position(sc.nextInt(), sc.nextInt());
			}
			move(currentPlayer.getCurrentPosition(), toMoveTo);
			break;
		case "Guess":
			System.out.println("Yes");
			break;
		case "Accuse":
			System.out.println("Okay");
			break;
		default:
			System.out.println("Invalid option");
		}
		
	}

	/**
	 * Find next player to have a turn.
	 * 
	 * @return the next player
	 */
	public Player nextPlayer() {
		int index = players.indexOf(currentPlayer);
		if(index >= players.size()) {
			index = 0;
		}
		currentPlayer = players.get(index);
		return currentPlayer;
	}

}
