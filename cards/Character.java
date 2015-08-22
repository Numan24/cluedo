package cluedo.cards;

import javax.swing.ImageIcon;

import cluedo.Game;

public class Character implements Card {

	private String name;
	private ImageIcon icon;

	public Character(String name) {
		this.name = name;
		
		icon = makeImageIcon(name);
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

	
	public ImageIcon getIcon() {
		return icon;
	}
	
	private static ImageIcon makeImageIcon(String filename) {
		filename = filename.toLowerCase();
		java.net.URL imageURL = Game.class.getResource("images/cards/"+filename+".png");

		ImageIcon icon = null;
		if (imageURL != null) {
			icon = new ImageIcon(imageURL);
		}
		return icon;
	}
	
	
	
}
