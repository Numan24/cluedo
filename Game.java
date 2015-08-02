package cluedo;

import java.util.*;

import cluedo.action.Accuse;
import cluedo.action.Guess;
import cluedo.action.Move;
import cluedo.cards.Card;
import cluedo.cards.Character;
import cluedo.cards.Room;
import cluedo.cards.Weapon;
import cluedo.tile.*;

public class Game {
	
	private Board board;
	private List<Player> players;
	private Player currentPlayer;
	private List<Card> envelope = new ArrayList<Card>(); // the cards 
	
	private List<Room> rooms = new ArrayList<Room>();
	private List<Weapon> weapons = new ArrayList<Weapon>();
	private List<Character> characters = new ArrayList<Character>();
	
	public Game() {
		players = gameSetup();
		currentPlayer = players.get(0);
		
		//weapons
		weapons.add(new Weapon("Dagger"));
		weapons.add(new Weapon("Revolver"));
		weapons.add(new Weapon("Lead Pipe"));
		weapons.add(new Weapon("Rope"));
		weapons.add(new Weapon("Spanner"));
		weapons.add(new Weapon("Candlestick"));
		//rooms
		rooms.add(new Room("Kitchen", null, 'K', 'U'));
		rooms.add(new Room("Ball Room", null, 'B', 'T'));
		rooms.add(new Room("Conservatory", null, 'C', 'R'));
		rooms.add(new Room("Billiard Room", null, 'I', 'Q'));
		rooms.add(new Room("Library", null, 'A', 'P'));
		rooms.add(new Room("Study", null, 'S', 'Z'));
		rooms.add(new Room("Hall", null, 'H', 'Y'));
		rooms.add(new Room("Lounge", null, 'L', 'W'));
		rooms.add(new Room("Dining Room", null, 'D', 'V'));
		rooms.add(new Room ("Swimming Pool", null, 'X', 'G'));
		
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
		board = new Board(players,this);
	}
	
	
	private ArrayList<Player> gameSetup() {
		int numPlayers;
		Scanner input = new Scanner(System.in);
		System.out.println("Cluedo");
		System.out.println("============");
		System.out.println("");
		System.out.println("How many players? (2-6) ");
		numPlayers = input.nextInt();
		while(numPlayers > 6 || numPlayers < 2) {
			System.out.println("Please enter a number between 2 and 6.");
			numPlayers = input.nextInt();
		}
		ArrayList<Player> players = new ArrayList<Player>();
		for(int i = 1; i <= numPlayers; i++) {
			
			System.out.println("Enter name for player number "+i+":");
			String name = input.next();
			for(Player p : players) {
				while(p.getName().equals(name)) {
					System.out.println("Name already in use");
					System.out.println("Enter new name: ");
					name = input.next();
				}
			}
			Player player = new Player(name, this);
			players.add(player);
		}
		return players;
	}
	
	public void play() {
		board.redraw();
		haveNextTurn(currentPlayer);
		currentPlayer = nextPlayer();
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
		
		List<String> options = player.getOptions();
		
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
	 * @return 0 is a fail 1 is a pass and 2 is display hand
	 */
	private int calculatePlay(String option) {
		option = option.toLowerCase();
		switch(option) {
		case "move":
			doMove();
			break;
		case "guess":
			doGuess();
			break;
		case "accuse":
			doAccuse();
			break;
		case "hand":
			currentPlayer.displayHand();
			return 2;
		case "enter":
			for(Tile t: currentPlayer.adjacentTiles()) {
				if(t instanceof DoorTile) {
					doEnter(t);
				}
			}
			break;
		case "leave":
			doLeave();
			break;
		default:
			return 0; // an invalid option was passed so fail by returning 0
		}
		return 1; 
	}
	
	public void doLeave(){
		Room r = currentPlayer.getRoom();
		//NEED TO SET TO POSITION BEFORE ENTERING.
		currentPlayer.setRoom(null);
	}

	public void doEnter(Tile t){
		DoorTile tile = (DoorTile) t;
		Room room = tile.getRoom();
		room.addPlayer(currentPlayer);
		currentPlayer.setRoom(tile.getRoom());
		
		//NEED TO REMOVE FROM CURRENT POSITION AND SHIT
		
		System.out.println("You are now in the "+currentPlayer.getRoom().getName());
		System.out.println("Do you want to make a guess? [Y / N]");
		Scanner sc = new Scanner(System.in);
		String answer = sc.next();
		while(!(answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N"))){
			System.out.println("Invalid choice. Please try again.");
			answer = sc.next();
		}
		if(answer.equalsIgnoreCase("Y")){
			doGuess();
			
		}
	}
	
	public void doMove() {
		System.out.println("Player pos: "+currentPlayer.getCurrentPosition());
		for(int i= currentPlayer.getRoll(); i > 0; i--){
			System.out.println("You have "+i+" rolls left.");
			Move playerMove = new Move(this, currentPlayer);
			//if player chooses to stop moving.
			if(currentPlayer.getRoll()==0){break;}
			while(!playerMove.isValid()){
				System.out.println("Unsuccessful Movement.");
				playerMove = new Move(this, currentPlayer);
			}
			board.move(playerMove.getOldPosition(), playerMove.getNewPosition());
			board.redraw();
			System.out.println("Your new position: "+playerMove.getNewPosition());
		}
		
		//==== THIS IS UGLY AND I AM SORRY ========
		for(Tile t: currentPlayer.adjacentTiles()) {
			if(t instanceof DoorTile) {
				Scanner sc = new Scanner(System.in);
				DoorTile dt = (DoorTile) t;
				System.out.println("Would you like to enter: "+dt.getRoom().getName()+" [Y / N]");
				String answer = sc.next();
				while(!(answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N"))){
					System.out.println("Invalid choice. Please try again.");
					answer = sc.next();
				}
				if(answer.equalsIgnoreCase("Y")){
					doEnter(t);
					break;
				}
				else{break;}
				
			}
		}
	}
	
	public void doGuess() {
		if(currentPlayer.getRoom() != null) {
			Guess guess = new Guess(this, currentPlayer);
			guess.isValid();
		}
		else {System.out.println("Must be in a room to make a suggestion!");}
	}
	
	public void doAccuse() {
		Accuse playerAccusation = new Accuse(this, currentPlayer);
		if(playerAccusation.isValid()){
			System.out.println("Correct accusation! \n"+currentPlayer.getName()+" wins!");
			Main.gameFinished = true;
		} else{
			System.out.println("Incorrect accusation! "+currentPlayer.getName()+" loses");
			players.remove(currentPlayer);
		} 
	}
	
	
	/**
	 * Find next player to have a turn.
	 * 
	 * @return the next player
	 */
	public Player nextPlayer() {
		int index = players.indexOf(currentPlayer);
		if(index == players.size()-1) {
			return players.get(0);
		}
		else {
			return players.get(index+1);
		}
	}
	
	public Player nextPlayer(Player player) {
		int index = players.indexOf(player);
		if(index == players.size()-1) {
			return players.get(0);
		}
		else {
			return players.get(index+1);
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
		
		envelope.add(rooms.get(0));	
		envelope.add(weapons.get(0));
		envelope.add(characters.get(0));
		
		for(Card c: envelope){
			System.out.println(c);
		}
		
		allcards.addAll(rooms.subList(1, rooms.size()-1));
		allcards.addAll(weapons.subList(1, weapons.size()-1));
		allcards.addAll(characters.subList(1, characters.size()-1));
		Player p = currentPlayer;
		for(Card c: allcards){
			p.addCard(c);
			p = nextPlayer(p);
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

	public Tile[][] getBoard() {
		return board.getBoard();
	}
	
	public Player[][] getPlayerPositions() {
		return board.getPlayerPositions();
	}
}
