package cluedo.action;

import cluedo.Game;
import cluedo.Player;

public class Hand extends Action {

	public Hand(Game game, Player player) {
		super(game, player);
	}

	@Override
	public boolean isValid() {
		if(player.hasLost() || player.getHand().size() < 1) {
			return false;
		}
		return true;
	}

	@Override
	public void run() {
		player.displayHand();
	}

	@Override
	public boolean endsTurn() {
		return false;
	}

}
