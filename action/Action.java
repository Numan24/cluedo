package cluedo.action;

import cluedo.*;

public abstract class Action {

	private Board board;
	private Player player;
	
	public Action(Board board, Player player) {
		this.board = board;
		this.player = player;
	}
}
