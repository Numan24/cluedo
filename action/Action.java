package cluedo.action;

import cluedo.*;

public abstract class Action {

	protected Board board;
	protected Player player;
	
	public Action(Board board, Player player) {
		this.board = board;
		this.player = player;
	}
	
	public abstract boolean isValid();
}
