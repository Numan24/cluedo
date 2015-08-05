package cluedo.action;

import cluedo.Game;
import cluedo.Player;

public class End extends Action {

	public End(Game game, Player player) {
		super(game, player);
	}

	@Override
	public boolean isValid() {
		return player.getRoll() == 0;
	}

	@Override
	public void run() {
		return;
	}

	@Override
	public boolean endsTurn() {
		return true;
	}

}
