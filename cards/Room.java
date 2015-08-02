package cluedo.cards;

import java.util.ArrayList;
import java.util.List;

import cluedo.Player;
import cluedo.tile.*;

public class Room implements Card {
	
	private Room connectedTo;
	private String name;
	
	private List<Player> players = new ArrayList<Player>();
	private List<RoomTile> roomTiles = new ArrayList<RoomTile>();
	private List<DoorTile> doors = new ArrayList<DoorTile>();
	
	private final char id;
	private final char doorID;

	public Room(String name, Room connectedTo, char id, char doorID) {
		this.name = name;
		this.connectedTo = connectedTo;
		this. id = id;
		this.doorID = doorID;
	}
	
	public void addTile(RoomTile tile) {
		roomTiles.add(tile);
	}
	
	public void addPlayer(Player p) {
		players.add(p);
	}
	
	public List<Player> getPlayers() {
		return players;
	}

	public String getName() {
		return name;
	}
	
	public void setConnectedTo(Room r){
		connectedTo = r;
	}
	
	public Room getConnectedTo() {
		return connectedTo;
	}

	public boolean equals(Object o) {
		if(o instanceof Room) {
			Room room = (Room) o;
			if(room.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public char getId() {
		return id;
	}
	
	public void addDoor(DoorTile door) {
		doors.add(door);
	}
	
	public List<DoorTile> getDoors() {
		return doors;
	}
 

	@Override
	public String toString() {
		return "[" +name+ "]";
	}

	
	public char getDoorID() {
		return doorID;
	}
	
	public List<RoomTile> getRoomTiles(){
		return roomTiles;
	}
	
	
}
