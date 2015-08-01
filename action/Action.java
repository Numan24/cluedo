package cluedo.action;

import cluedo.*;

public abstract class Action {

	protected Game game;
	protected Player player;
	
	public Action(Game game, Player player) {
		this.game = game;
		this.player = player;
	}
	
	public abstract boolean isValid();
}
