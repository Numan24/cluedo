package cluedo.tile;

import cluedo.Player;

public abstract class Tile {

	private final int x,y;
	private Player player;
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
