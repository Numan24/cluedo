package cluedo.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cluedo.Board;
import cluedo.Player;
import cluedo.cards.Card;
import cluedo.cards.Character;
import cluedo.cards.Room;
import cluedo.cards.Weapon;

public class Accuse extends Action {

	private List<Card> guess = new ArrayList<Card>();
	
	
	public Accuse(Board board, Player player) {
		super(board, player);
		setup();

	}
	

	/**
	 * Asks user for a weapon, room and character until they pick a valid one of each. 
	 * Saves resulting 3 cards into array.
	 */
	public void setup(){
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
		

		guess.add(roomGuess);
		guess.add(weaponGuess);
		guess.add(charGuess);
	}
	
	private Weapon validWeapon(String guess) {
		for(Weapon w: board.getWeapons()){
			if(w.getName().toLowerCase().equals(guess.toLowerCase())){return w;}
		}
		return null;
	}
	
	private Room validRoom(String guess) {
		for(Room r: board.getRooms()){
			if(r.getName().toLowerCase().equals(guess.toLowerCase())){return r;}
		}
		return null;
	}
	
	private Character validChar(String guess) {
		for(Character c: board.getCharacters()){
			if(c.getName().toLowerCase().equals(guess.toLowerCase())){return c;}
		}
		return null;
	}

	public boolean isValid() {
		return board.checkGuess(guess);
	}

}
