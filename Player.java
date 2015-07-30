package cluedo;

import java.util.*;

public class Player {

	String name;
	private List<Card> hand;

	public Player(String name) {
		this.name = name;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	public void addCard(Card card) {
		this.hand.add(card);
	}

	/**
	 * get the current options that the player has at their given position.
	 * @return array of options
	 */
	public String[] getOptions() {
		String[] options = new String[3];
		options[0] = "Hello";
		options[1] = "Yes";
		options[2] = "okay";
		return options;
	}

}
