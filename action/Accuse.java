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
		String weapon = Input.getString("Guess a Weapon: [Dagger, Revolver, Candlestick, Rope, Spanner, Leadpipe] ");
		Weapon weaponGuess = validWeapon(weapon);
		while(weaponGuess==null){
			System.out.println("Invalid Weapon.");
			weapon = Input.getString("Guess a Weapon: [Dagger, Revolver, Candlestick, Rope, Spanner, Leadpipe] ");
			weaponGuess = validWeapon(weapon);
		}


		String room = Input.getString("Guess a Room: [Library, Kitchen, Ball Room, Billiard Room, Conservatory, Study, Hall, Dining Room]");
		Room roomGuess = validRoom(room);
		while(roomGuess==null){
			System.out.println("Invalid Room.");
			room = Input.getString("Guess a Room: [Library, Kitchen, Ball Room, Billiard Room, Conservatory, Study, Hall, Dining Room]");
			roomGuess = validRoom(room);
		}

		String character = Input.getString("Guess a Character: [Miss Scarlett, Colonel Mustard, Mrs. White, The Reverand Green, Mrs. Peacock, Professor Plum]");
		Character charGuess = validChar(character);
		while(charGuess==null){
			System.out.println("Invalid Character.");
			character = Input.getString("Guess a Character: [Miss Scarlett, Colonel Mustard, Mrs. White, The Reverand Green, Mrs. Peacock, Professor Plum]");
			charGuess = validChar(character);
		}

		guess.add(roomGuess);
		guess.add(weaponGuess);
		guess.add(charGuess);
	}

	private Weapon validWeapon(String guess) {
		for(Weapon w: game.getWeapons()){
			if(w.getName().toLowerCase().equals(guess.toLowerCase())){return w;}
		}
		return null;
	}

	private Room validRoom(String guess) {
		for(Room r: game.getRooms()){
			if(r.getName().toLowerCase().equals(guess.toLowerCase())){return r;}
		}
		return null;
	}

	private Character validChar(String guess) {
		for(Character c: game.getCharacters()){
			if(c.getName().toLowerCase().equals(guess.toLowerCase())){return c;}
		}
		return null;
	}

	public boolean isValid() {
		return game.checkGuess(guess);
	}


	public boolean outcome() {
		if(isValid()){
			System.out.println("Correct accusation! \n"+player.getName()+" wins!");
			return true;
		} else{
			System.out.println("Incorrect accusation! "+player.getName()+" loses");
			return false;
		}

	}

	public boolean endsTurn(){
		return true;
	}

}
