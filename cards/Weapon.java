package cluedo.cards;

public class Weapon implements Card{

	private String name;

	public Weapon(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean equals(Object o) {
		if(o instanceof Weapon) {
			Weapon weapon = (Weapon) o;
			if(weapon.getName().equals(name)) {
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
