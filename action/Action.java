package cluedo.action;

import cluedo.*;

public abstract class Action {
	
	/*
	 * Represents an action
	 * Actions are what players are able to perform on their turn
	 */

	protected Game game;
	protected Player player;

	public Action(Game game, Player player) {
		this.game = game;
		this.player = player;
	}

	/**
	 * returns ture if the action is able to be carried out
	 * @return
	 */
	public abstract boolean isValid();

	/**
	 * performs the action
	 */
	public abstract void run();

	/**
	 * returns true if on completing the action the players turn has ended. EG: a guess action would end the turn
	 * @return
	 */
	public abstract boolean endsTurn();
}
