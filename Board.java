package Cluedo;

import java.util.*;

public class Board {

	private List<Card> win; // the cards 
	
	private List<Player> players; // players in the current game
	
	private int[][] board; // hard code board please
	
	private Player currentPlayer;

	
	/**
	 * Construct a board from a list of players
	 * @param players - list of players in the game
	 */
	public Board(List<Player> players) {
		this.players = players;
		currentPlayer = players.get(0);
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

	/**
	 * Determine what happens for a given option.
	 * 
	 * @param option - the option selected by the player
	 */
	private void calculatePlay(String option) {
		switch(option) {
		case "Hello":
			System.out.println("Hello");
			break;
		case "Yes":
			System.out.println("Yes");
			break;
		case "Okay":
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
