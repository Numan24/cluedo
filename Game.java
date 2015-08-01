package cluedo;

import java.util.*;

import cluedo.action.Accuse;
import cluedo.action.Guess;
import cluedo.action.Move;
import cluedo.cards.Card;
import cluedo.cards.Character;
import cluedo.cards.Room;
import cluedo.cards.Weapon;

public class Game {
	
	private Board board;
	private List<Player> players;
	private Player currentPlayer;
	private List<Card> envelope = new ArrayList<Card>(); // the cards 
	
	private List<Room> rooms = new ArrayList<Room>();
	private List<Weapon> weapons = new ArrayList<Weapon>();
	private List<Character> characters = new ArrayList<Character>();
	
	public Game(List<Player> players) {
		this.players = players;
		
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
		board = new Board(players);
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
		board.redraw();
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
			board.move(playerMove.getOldPosition(), playerMove.getNewPosition());
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
				//If all other players have lost.
				if(players.size()==1){
					System.out.println(players.get(0).getName()+" wins!");
					Main.gameFinished = true;
				}
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
	
	
    /**
     * Shuffles cards randomly and distributes to all players as well as deciding the winning 3 cards.
     * 
     */
	public void distributeCards(){
		ArrayList<Card> allcards = new ArrayList<Card>();
		
		Collections.shuffle(rooms);
		Collections.shuffle(weapons);
		Collections.shuffle(characters);
		
		envelope.add(rooms.get(0));	
		envelope.add(weapons.get(0));
		envelope.add(characters.get(0));
		
		for(Card c: envelope){
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
	 * Check to see if a guess was correct.
	 * 
	 * @param guess - the cards that the player guessed.
	 * @return
	 */
	public boolean checkGuess(List<Card> guess) {
		return envelope.containsAll(guess);
	}
	

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
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
	
	public List<Player> getPlayers() {
		return players;
	}

	public char[][] getBoard() {
		return board.getBoard();
	}
	
	public Player[][] getPlayerPositions() {
		return board.getPlayerPositions();
	}
}
