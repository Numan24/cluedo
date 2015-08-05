package cluedo.action;

import cluedo.Game;
import cluedo.Player;

public class End extends Action {
	
	/*
	 * Represents an end turn action
	 * used when a player want to (or has to) end their turn
	 */

	public End(Game game, Player player) {
		super(game, player);
	}

	@Override
	public boolean isValid() {
		return true;
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
