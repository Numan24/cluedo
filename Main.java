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
			for(Player p : game.getPlayers()) {
				if(p.hasLost()) {
					i++;
				}
				else {
					play = p;
				}
			}
			if(i == game.getPlayers().size()-1){
				System.out.println("Game Over!\n"+play.getName()+" has won!");
				gameFinished = true;
				continue;
			}
		}
	}







}
