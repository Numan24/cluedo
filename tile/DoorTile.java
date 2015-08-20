package cluedo.tile;

import java.awt.Color;

import cluedo.cards.Room;

public class DoorTile extends Tile {

	private final Room room;
	//private final Color color;

	public DoorTile(int x, int y, Room room) {
		super(x, y);
		this.room = room;
		this.color = Color.LIGHT_GRAY;
	}

	public String toString() {
		return room.getDoorID()+"";
	}

	public Room getRoom() {
		return room;
	}
}
