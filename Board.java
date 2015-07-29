package cluedo;

import java.util.*;

public class Board {

	private List<Card> win; // the cards 
	
	private List<Player> players; // players in the current game
	
	private int[][] board; // hard code board please

	public Board(List<Player> players) {
		this.players = players;
	}
	
	public boolean checkGuess(List<Card> guess) {
		return win.containsAll(guess);
	}

}
