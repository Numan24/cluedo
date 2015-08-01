package cluedo.cards;

public class Character implements Card {

	private String name;

	public Character(String name) {
		this.name = name;
	}

	/**
	 * name of the character.
	 * @return character name
	 */
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

	@Override
	public String toString() {
		return "[" +name+ "]";
	}

	
}
