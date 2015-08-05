package cluedo;

public class Main {

	static boolean gameFinished = false;

	public static void main(String[] args) {
		Game game = new Game();
		// game loop
		while(!gameFinished) {
			//board.redraw();
			game.play();
			int i = 0;
			Player play = null;
			// calculate how many players have lost
			for(Player p : game.getPlayers()) {
				if(p.hasLost()) {
					i++;
				}
				else {
					play = p;
				}
			}
			if(i == game.getPlayers().size()-1){ // if there is only one player left end the game and that player wins
				System.out.println("Game Over!\n"+play.getName()+" has won!");
				gameFinished = true;
				continue;
			}
		}
	}







}
