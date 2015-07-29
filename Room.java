package Cluedo;

public class Room implements Card {
	boolean hasStairs;
	String name;

	public Room(String name, boolean hasStairs) {
		this.name = name;
		this.hasStairs = hasStairs;
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
