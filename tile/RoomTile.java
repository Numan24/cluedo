package cluedo.tile;

import java.awt.Color;

import cluedo.cards.Room;

public class RoomTile extends Tile {

	private final Room room;

	public RoomTile(int x, int y, Room room) {
		super(x, y);
		this.room = room;
		color = Color.BLUE;
	}


	public Room getRoom() {
		return room;
	}

	public String toString() {
		return room.getId()+"";
	}

}
