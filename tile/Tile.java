package cluedo.tile;

import java.awt.Color;

import cluedo.Player;
import cluedo.Position;

/**
 * used to represent different tiles on the board
 *
 */
public abstract class Tile {

	protected final int x;
	protected final int y;
	private Player player;
	protected Color color;

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		this.color = Color.black;
	}

	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}

	public Position getPosition(){
		return new Position(y,x);
	}
	
	public Color getColor() {
		return color;
	}


}
