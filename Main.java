package cluedo;

import java.util.*;

public class Main {

	static boolean gameFinished = false;
	
	public static void main(String[] args) {
		Game game = new Game();
		
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




	

	
}
