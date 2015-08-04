package cluedo.tile;

import java.util.ArrayList;
import java.util.List;

import cluedo.cards.Room;

public class DoorTile extends Tile {

	private final Room room;

	public DoorTile(int x, int y, Room room) {
		super(x, y);
		this.room = room;
	}

	public String toString() {
		return room.getDoorID()+"";
	}

	public Room getRoom() {
		return room;
	}
}
