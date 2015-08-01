package cluedo;

import java.util.*;

public class Main {

	static boolean gameFinished = false;
	
	public static void main(String[] args) {
		ArrayList<Player> players = gameSetup();
		Game game = new Game(players);
		
		// game loop
		while(!gameFinished) {
			//board.redraw();
			game.play();
			if(game.getPlayers().size() == 1){
				System.out.println("Game Over!\n"+game.getPlayers().get(0).getName()+" has won!");
				gameFinished = true;
				continue;
			}
		}
	}




	/**
	 * Start the game. Create the players in the game. 
	 * Then construct a board for the game with the players.
	 * 
	 * @return - board for the game with the players
	 */
	private static ArrayList<Player> gameSetup() {
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
			Player player = new Player(name);
			players.add(player);
		}
		return players;
	}
	
}
