package cluedo.cards;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import cluedo.Game;
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
	
	private ImageIcon icon;

	public Room(String name, Room connectedTo, char id, char doorID) {
		this.name = name;
		this.connectedTo = connectedTo;
		this. id = id;
		this.doorID = doorID;
		icon = makeImageIcon(name);
	}

	public void addTile(RoomTile tile) {
		roomTiles.add(tile);
	}

	public void addPlayer(Player p) {
		players.add(p);
	}

	public void removePlayer(Player p) {
		players.remove(p);
		for(RoomTile t : roomTiles) {
			if(t.getPlayer()!= null && t.getPlayer().equals(p)) {
				t.setPlayer(null);
			}
		}
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
	
	public ImageIcon getIcon() {
		return icon;
	}
	
	private static ImageIcon makeImageIcon(String filename) {
		filename = filename.toLowerCase();
		java.net.URL imageURL = Game.class.getResource("images/cards/"+filename+".png");
		System.out.println("images/cards/"+filename+".png");
		ImageIcon icon = null;
		if (imageURL != null) {
			icon = new ImageIcon(imageURL);
		}
		if(icon == null){System.out.println("card is null");
			System.out.println(imageURL);
		}
		return icon;
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
