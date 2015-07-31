package cluedo.cards;

public class Room implements Card {
	Room connectedTo;
	String name;

	public Room(String name, Room connectedTo) {
		this.name = name;
		this.connectedTo = connectedTo;
	}


	public String getName() {
		return name;
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
}
