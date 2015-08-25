package cluedo.cards;

import javax.swing.ImageIcon;
import cluedo.Game;

/**
 * represents a weapon card
 */
public class Weapon implements Card{

	private String name;
	private ImageIcon icon;

	public Weapon(String name) {
		this.name = name;
		icon = makeImageIcon(name);
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
		if(icon == null){System.out.println("card is null");}
		return icon;
	}
	

}
