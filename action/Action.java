package cluedo.action;

import cluedo.*;

public abstract class Action {

	private Board board;
	
	public Action(Board board) {
		this.board = board;
	}
}
