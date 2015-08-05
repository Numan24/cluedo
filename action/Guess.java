package cluedo.action;

import java.util.ArrayList;
import java.util.List;

import cluedo.Game;
import cluedo.Input;
import cluedo.Player;
import cluedo.cards.Card;
import cluedo.cards.Character;
import cluedo.cards.Weapon;

public class Guess extends Action {
	private List<Card> guess = new ArrayList<Card>();

	public Guess(Game game, Player player) {
		super(game, player);
	}

	/**
	 * asks the user for a weapon and a character to guess as the murderer
	 * does not ask for a room as the room the player is in is used
	 * Then checks to see if the other players have the cards
	 */
	public void run(){
		String weapon = Input.getLine("Guess a Weapon: [Dagger, Revolver, Candlestick, Rope, Spanner, Leadpipe] ");
		Weapon weaponGuess = validWeapon(weapon);
		while(weaponGuess==null){
			System.out.println("Invalid Weapon.");
			weapon = Input.getLine("Guess a Weapon: [Dagger, Revolver, Candlestick, Rope, Spanner, Leadpipe] ");
			weaponGuess = validWeapon(weapon);
		}

		String character = Input.getLine("Guess a Character: [Miss Scarlett, Colonel Mustard, Mrs. White, The Reverand Green, Mrs. Peacock, Professor Plum]");
		Character charGuess = validChar(character);
		while(charGuess==null){
			System.out.println("Invalid Character.");
			character = Input.getLine("Guess a Character: [Miss Scarlett, Colonel Mustard, Mrs. White, The Reverand Green, Mrs. Peacock, Professor Plum]");
			charGuess = validChar(character);
		}
		guess.add(player.getRoom());
		guess.add(weaponGuess);
		guess.add(charGuess);
		//checks the hands of the other players to see if they have any of the cards guessed
		for(Player p : game.getPlayers()) {
			if(p.equals(game.getCurrentPlayer())){continue;}
			for(Card c : p.getHand()) {
				for(Card card : guess) {
					if(card.equals(c)) {
						System.out.println(p.getName()+" has one (or more) of the cards\n");
					}
				}
			}
		}
	}

	/**
	 * checks if inputed weapon is valid
	 * @param guess - the weapon
	 * @return weapon object or null if it doesn't exist
	 */
	private Weapon validWeapon(String guess) {
		for(Weapon w: game.getWeapons()){
			if(w.getName().toLowerCase().equals(guess.toLowerCase())){return w;}
		}
		return null;
	}

	/**
	 * checks if inputed character is valid
	 * @param guess - the character
	 * @return character object or null if it doesn't exist
	 */
	private Character validChar(String guess) {
		for(Character c: game.getCharacters()){
			if(c.getName().toLowerCase().equals(guess.toLowerCase())){return c;}
		}
		return null;
	}

	public boolean isValid() {
		return player.getRoom() != null;
	}

	public boolean endsTurn(){
		return true;
	}

}
