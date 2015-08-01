package cluedo.action;

import cluedo.Game;
import cluedo.Player;

public class Enter extends Action {

	public Enter(Game game, Player player) {
		super(game, player);
	}

	
	public boolean isValid() {
		return false;
	}
}
