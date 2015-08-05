package cluedo.tile;

import cluedo.Player;
import cluedo.Position;

public abstract class Tile {

	protected final int x;
	protected final int y;
	private Player player;
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Player getPlayer() {
		return player;
	}

	public Position getPosition(){
		return new Position(y,x);
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
}
