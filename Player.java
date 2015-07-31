package cluedo;

import java.util.*;

import cluedo.Main.Direction;
import cluedo.cards.Card;

public class Player {

	String name;
	int roll;
	private List<Card> hand;
	private Position currentPosition;

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
	

	public void move(Position p){
			currentPosition = p;
	}
	
	public boolean isValidMove(Position newPos){
		return false;
	}
	

	/**
	 * get the current options that the player has at their given position.
	 * @return array of options
	 */
	public String[] getOptions() {
		String[] options = new String[3];
		options[0] = "Guess";
		options[1] = "Accuse";
		options[2] = "Move";
		return options;
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}



}
