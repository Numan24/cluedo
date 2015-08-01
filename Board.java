package cluedo;

import java.io.File;
import java.util.*;

import cluedo.action.Accuse;
import cluedo.action.Guess;
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
		
		setBoard(createBoard());
		playerPositions = setPlayerPositions();
		
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
		setPlayerPositions();
	}
	
	/**
	 * Creates the board array by reading from the board text file
	 * 
	 * @return the array for the board
	 */
	private char[][] createBoard() {
		try {
			Scanner scan = new Scanner(new File("board.txt"));
			char[][] board = new char[25][25];
			int index = 0;
			while(scan.hasNextLine()) {
				String s = scan.nextLine();
				if(s.startsWith("#")){continue;}
				char[] line = s.toCharArray();
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
	
	
	private void redraw(){
		for(int i = 0; i < getBoard().length; i++){
			for(int j = 0; j < getBoard()[0].length; j++){
				if(playerPositions[i][j]==null){
					System.out.print(getBoard()[i][j]+" ");
				} else{System.out.print(players.indexOf(playerPositions[i][j])+1 +" ");}
			}
			System.out.print("\n");
		}
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
	
	
	public Player[][] setPlayerPositions(){
		Player[][] board = new Player[25][25];
		int index = 0;
		for(Player p: players){
			board[index][index] = p;
			p.move(new Position(index, index));
			index++;
		}
		return board;
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
		if(players.size()==1){
			System.out.println(players.get(0).getName()+" wins!");
			Main.gameFinished = true;
		}
		redraw();
		
		
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
		int valid = calculatePlay(option);
		while(valid == 0 || valid == 2) {
			if(valid == 0){
			System.out.println("Invalid option");
			System.out.println("Please enter a new option");
			option = input.next();
			valid = calculatePlay(option);
			}
			else {
				option = input.next();
				valid = calculatePlay(option);
			}
		}
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
	private int calculatePlay(String option) {
		option = option.toLowerCase();
		switch(option) {
		case "move":
			System.out.println("Player pos: "+currentPlayer.getCurrentPosition());
			Move playerMove = new Move(this, currentPlayer);
			while(!playerMove.isValid()){
				System.out.println("Unsuccessful Movement.");
				playerMove = new Move(this, currentPlayer);
			}
			move(playerMove.getOldPosition(), playerMove.getNewPosition());
			break;
		case "guess":
			//currentPlayer.setRoom(rooms.get(0));
			if(currentPlayer.getRoom() != null) {
				Guess guess = new Guess(this, currentPlayer);
				guess.isValid();
				break;
			}
			else {System.out.println("Must be in a room to make a suggestion!");}
			break;
		case "accuse":
			Accuse playerAccusation = new Accuse(this, currentPlayer);
			if(playerAccusation.isValid()){
				System.out.println("Correct accusation! "+currentPlayer.getName()+" wins");
				Main.gameFinished = true;
			} else{
				System.out.println("Incorrect accusation! "+currentPlayer.getName()+" loses");
				players.remove(currentPlayer);
			} 
			break;
		case "hand":
			currentPlayer.displayHand();
			return 2;
		default:
			return 0;
		}
		return 1;
	}
	


	/**
	 * Find next player to have a turn.
	 * 
	 * @return the next player
	 */
	public Player nextPlayer() {
		int index = players.indexOf(currentPlayer);
		if(index == players.size()-1) {
			currentPlayer = players.get(0);
		}
		else {
			currentPlayer = players.get(index+1);
		}
		return currentPlayer;
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public List<Player> getPlayers() {
		return players;
	}

	public List<Weapon> getWeapons() {
		return weapons;
	}

	public void setWeapons(List<Weapon> weapons) {
		this.weapons = weapons;
	}

	public List<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	public Player[][] getPlayerPositions() {
		return playerPositions;
	}

}
