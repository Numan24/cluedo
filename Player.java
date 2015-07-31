package cluedo;

import java.util.*;

import cluedo.Main.Direction;
import cluedo.cards.Card;

public class Player {

	String name;
	private List<Card> hand;
	int xPos, yPos;

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
	 * Changes character x and y depending on direction given.
	 * @param Direction d. The direction to move the char in.
	 */
	public void move(Direction d){
		switch(d){
		case NORTH:	yPos--; break;
		case EAST:	xPos++; break;
		case SOUTH:	yPos++; break;
		case WEST:	xPos--; break;
		default:	System.out.println("Invalid Direction");
		}
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

}
