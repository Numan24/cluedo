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

public class Guess extends Action {
	private List<Card> guess = new ArrayList<Card>();

	public Guess(Board board, Player player) {
		super(board, player);
		setup();
	}
	
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
		
		
		System.out.println("Guess a Character: [Miss Scarlett, Colonel Mustard, Mrs. White, The Reverand Green, Mrs. Peacock, Professor Plum]");
		String character = sc.nextLine();
		Character charGuess = validChar(character);
		while(charGuess==null){
			System.out.println("Invalid Character.");
			System.out.println("Guess a Character: [Miss Scarlett, Colonel Mustard, Mrs. White, The Reverand Green, Mrs. Peacock, Professor Plum]");
			character = sc.nextLine();
			charGuess = validChar(character);
		}
		guess.add(player.getRoom());
		guess.add(weaponGuess);
		guess.add(charGuess);
	}
	
	private Weapon validWeapon(String guess) {
		for(Weapon w: board.getWeapons()){
			if(w.getName().toLowerCase().equals(guess.toLowerCase())){return w;}
		}
		return null;
	}
	
	private Character validChar(String guess) {
		for(Character c: board.getCharacters()){
			if(c.getName().toLowerCase().equals(guess.toLowerCase())){return c;}
		}
		return null;
	}
	
	public List<Card> getCards() {
		return guess;
	}
	
	public boolean isValid() {
		for(Player p : board.getPlayers()) {
			if(p.equals(board.getCurrentPlayer())){continue;}
			for(Card c : p.getHand()) {
				for(Card card : guess) {
					if(card.equals(c)) {
						System.out.println(p.getName()+" has one of the cards\n");
						return true;
					}
				}
			}
		}
		return false;
	}

}
