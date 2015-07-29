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

}
