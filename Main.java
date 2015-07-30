package cluedo;

import java.util.*;

public class Main {


	public static void main(String[] args) {
		// ask for players
		//create board
		Board board = gameSetup();
		boolean gameFinished = false;
		while(!gameFinished) {
			Player player = board.nextPlayer();
			board.haveNextTurn(player);
			
		}
	}


	/**
	 * Start the game. Create the players in the game. 
	 * Then construct a board for the game with the players.
	 * 
	 * @return - board for the game with the players
	 */
	private static Board gameSetup() {
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
		List<Player> players = new ArrayList<Player>();
		for(int i = 1; i <= numPlayers; i++) {
			
			System.out.println("Enter name for player number "+i+":");
			String name = input.next();
			Player player = new Player(name);
			players.add(player);
		}
		input.close();
		return new Board(players);
	}
	
}
