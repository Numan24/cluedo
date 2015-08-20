package cluedo.tile;

import java.awt.Color;

public class FloorTile extends Tile {

	public FloorTile(int x, int y) {
		super(x, y);
		color = Color.DARK_GRAY;
	}


	public String toString() {
		return "O";
	}

}
