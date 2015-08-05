package cluedo.action;

import java.util.ArrayList;
import java.util.List;

import cluedo.Game;
import cluedo.Input;
import cluedo.Player;
import cluedo.cards.*;
import cluedo.cards.Character;

public class Accuse extends Action {

	private List<Card> guess = new ArrayList<Card>();


	public Accuse(Game game, Player player) {
		super(game, player);
	}


	/**
	 * Asks user for a weapon, room and character until they pick a valid one of each.
	 * Saves resulting 3 cards into array.
	 */
	public void run(){
		String weapon = Input.getLine("Guess a Weapon: [Dagger, Revolver, Candlestick, Rope, Spanner, Leadpipe] ");
		Weapon weaponGuess = validWeapon(weapon);
		while(weaponGuess==null){
			System.out.println("Invalid Weapon.");
			weapon = Input.getLine("Guess a Weapon: [Dagger, Revolver, Candlestick, Rope, Spanner, Leadpipe] ");
			weaponGuess = validWeapon(weapon);
		}


		String room = Input.getLine("Guess a Room: [Library, Kitchen, Ball Room, Billiard Room, Conservatory, Study, Hall, Dining Room]");
		Room roomGuess = validRoom(room);
		while(roomGuess==null){
			System.out.println("Invalid Room.");
			room = Input.getLine("Guess a Room: [Library, Kitchen, Ball Room, Billiard Room, Conservatory, Study, Hall, Dining Room]");
			roomGuess = validRoom(room);
		}

		String character = Input.getLine("Guess a Character: [Miss Scarlett, Colonel Mustard, Mrs. White, The Reverand Green, Mrs. Peacock, Professor Plum]");
		Character charGuess = validChar(character);
		while(charGuess==null){
			System.out.println("Invalid Character.");
			character = Input.getLine("Guess a Character: [Miss Scarlett, Colonel Mustard, Mrs. White, The Reverand Green, Mrs. Peacock, Professor Plum]");
			charGuess = validChar(character);
		}

		guess.add(roomGuess);
		guess.add(weaponGuess);
		guess.add(charGuess);
		// if the outcome is that the guess was wrong then the player loses
		if(!outcome()) {
			player.lost(true);
		}
	}

	/**
	 * checks to see if the inputed weapon is a valid weapon
	 * @param guess - inputed string
	 * @return null if no weapon was found or the weapon object
	 */
	private Weapon validWeapon(String guess) {
		for(Weapon w: game.getWeapons()){
			if(w.getName().toLowerCase().equals(guess.toLowerCase())){return w;}
		}
		return null;
	}
	/**
	 * checks to see if the inputed room is a valid room
	 * @param guess - inputed string
	 * @return null if no room was found or the room object
	 */
	private Room validRoom(String guess) {
		for(Room r: game.getRooms()){
			if(r.getName().toLowerCase().equals(guess.toLowerCase())){return r;}
		}
		return null;
	}
	/**
	 * checks to see if the inputed character is a valid character
	 * @param guess - inputed string
	 * @return null if no character was found or the character object
	 */
	private Character validChar(String guess) {
		for(Character c: game.getCharacters()){
			if(c.getName().toLowerCase().equals(guess.toLowerCase())){return c;}
		}
		return null;
	}
	
	/**
	 * checks validity of the guess
	 * @return
	 */
	private boolean isValidGuess() {
		return game.checkGuess(guess);
	}

	/**
	 * always returns true as an accuse action can be performed from anywhere
	 */
	public boolean isValid() {
		return true;
	}

	/**
	 * returns the outcome of the guess
	 * 
	 * @return true if the guess was correct and false if it wasn't
	 */
	public boolean outcome() {
		if(isValidGuess()){
			System.out.println("Correct accusation! \n"+player.getName()+" wins!");
			return true;
		} else{
			System.out.println("Incorrect accusation! "+player.getName()+" loses!");
			return false;
		}

	}

	/**
	 * Always returns true as an accuse action ends your turn
	 */
	public boolean endsTurn(){
		return true;
	}

}
