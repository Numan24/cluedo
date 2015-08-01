package cluedo;

import java.io.File;
import java.util.*;

import cluedo.action.Move;
import cluedo.cards.Card;
import cluedo.cards.Character;
import cluedo.cards.Room;
import cluedo.cards.Weapon;

public class Board {

	private List<Card> win = new ArrayList<Card>(); // the cards 
	
	private List<Player> players = new ArrayList<Player>(); // players in the current game
	
	private List<Room> rooms = new ArrayList<Room>();
	private List<Weapon> weapons = new ArrayList<Weapon>();
	private List<Character> characters = new ArrayList<Character>();
	
	private char[][] board; // hard code board please
	private Player[][] playerPositions;
	
	private Player currentPlayer;

	
	/**
	 * Construct a board from a list of players
	 * @param players - list of players in the game
	 */
	public Board(List<Player> players) {
		this.players = players;
		currentPlayer = players.get(0);
		board = createBoard();
		
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
		
		distributeCards();
	}
	
	/**
	 * Creates the board array by reading from the board text file
	 * 
	 * @return the array for the board
	 */
	private char[][] createBoard() {
		try {
			Scanner scan = new Scanner(new File("board.txt"));
			System.out.println("hello?");
			char[][] board = new char[25][25];
			int index = 0;
			while(scan.hasNextLine()) {
				String s = scan.nextLine();
				System.out.println(s);
				if(s.startsWith("#")){continue;}
				char[] line = scan.nextLine().toCharArray();
				board[index] = line;
				index++;
			}
			scan.close();
			return board;
		}catch(Exception e) {
			System.out.println("Error reading file:  "+ e);
		}
		return null;
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
		
		for(Card c: win){
			System.out.println(c);
		}
		
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
		System.out.println(currentPlayer.getName()+"'s turn.");
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

		switch(option) {
		case "Move":
			move();
			break;
		case "Guess":
			System.out.println("Yes");
			break;
		case "Accuse":
			if(accusation()){
				System.out.println(currentPlayer.getName()+" wins");
				Main.gameFinished = true;
			} else{System.out.println(currentPlayer.getName()+" loses");} 
			break;
		default:
			System.out.println("Invalid option");
		}
	}
	
	public void move(){
		Scanner sc = new Scanner(System.in);
		Move playerMove = null;
		Position toMoveTo = null;
		while(playerMove==null){
			System.out.println("Choose new coords to move to 'x y'");
			toMoveTo = new Position(sc.nextInt(), sc.nextInt());
			while(!currentPlayer.isValidMove(toMoveTo)){
				System.out.println("Invalid Move.");
				System.out.println("Choose new coords to move to 'x y'");
				toMoveTo = new Position(sc.nextInt(), sc.nextInt());
			}
			playerMove = new Move(this, currentPlayer, currentPlayer.getCurrentPosition(), toMoveTo);
			if(!playerMove.isValid()){playerMove = null;}
		}
		move(currentPlayer.getCurrentPosition(), toMoveTo);
	}
	
	public boolean accusation(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Guess a Weapon: [Dagger, Revolver, Candlestick, Rope, Spanner, Leadpipe] ");
		String weapon = sc.nextLine();
		Weapon weaponGuess = validWeapon(weapon);
		while(weaponGuess==null){
			System.out.println("Invalid Weapon.");
			System.out.println("Guess a Weapon: [Dagger, Revolver, Candlestick, Rope, Spanner, Leadpipe] ");
			weapon = sc.nextLine();
			weaponGuess = validWeapon(weapon);
		}
		
		
		System.out.println("Guess a Room: [Library, Kitchen, Ballroom, Billiard Room, Conservatory, Study, Hall, Dining Room]");
		String room = sc.nextLine();
		Room roomGuess = validRoom(room);
		while(roomGuess==null){
			System.out.println("Invalid Room.");
			System.out.println("Guess a Room: [Library, Kitchen, Ballroom, Billiard Room, Conservatory, Study, Hall, Dining Room]");
			room = sc.nextLine();
			roomGuess = validRoom(room);
		}
		
		
		
		System.out.println("Guess a Character: [Miss Scarlett, Colonel Mustard, Mrs. White, The Reverand Green, Mrs. Peacock, Professor Plum]");
		String character = sc.nextLine();
		Character charGuess = validChar(character);
		while(charGuess==null){
			System.out.println("Invalid Character.");
			System.out.println("Guess a Character: [Miss Scarlett, Colonel Mustard, Mrs. White, The Reverand Green, Mrs. Peacock, Professor Plum]");
			character = sc.nextLine();
			charGuess = validChar(character);
		}
		
		List<Card> guess = new ArrayList<Card>();
		guess.add(roomGuess);
		guess.add(weaponGuess);
		guess.add(charGuess);
		return checkGuess(guess);
		
	}
	
	
	
	private Weapon validWeapon(String guess) {
		for(Weapon w: weapons){
			if(w.getName().equals(guess)){return w;}
		}
		return null;
	}
	
	private Room validRoom(String guess) {
		for(Room r: rooms){
			if(r.getName().equals(guess)){return r;}
		}
		return null;
	}
	
	private Character validChar(String guess) {
		for(Character c: characters){
			if(c.getName().equals(guess)){return c;}
		}
		return null;
	}

	/**
	 * Find next player to have a turn.
	 * 
	 * @return the next player
	 */
	public Player nextPlayer() {
		//System.out.println("Current player: "+currentPlayer.getName());
		int index = players.indexOf(currentPlayer);
		if(index == players.size()-1) {
			currentPlayer = players.get(0);
		}
		else {
			currentPlayer = players.get(index+1);
		}
		
		//System.out.println("Current player: "+currentPlayer.getName());
		return currentPlayer;
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

}
