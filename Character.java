package cluedo;

public class Character implements Card {

	private String name;

	public Character(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean equals(Object o) {
		if(o instanceof Character) {
			Character character = (Character) o;
			if(character.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

}
