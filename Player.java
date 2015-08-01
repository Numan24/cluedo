package cluedo;

import java.util.*;

import cluedo.cards.Card;
import cluedo.cards.Room;

public class Player {

	private String name;
	int roll;
	private List<Card> hand = new ArrayList<Card>();
	private Position currentPosition;
	private Room room;

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
		//STILL NEED TO CHECK FOR OK POSITION ON BOARD, TOO.
		if(Math.abs(newPos.row() - currentPosition.row()) + Math.abs(newPos.col()-currentPosition.col()) > roll){
			return false;
		}
		return true;
	}

	/**
	 * get the current options that the player has at their given position.
	 * @return array of options
	 */
	public String[] getOptions() {
		String[] options = new String[4];
		options[0] = "Guess";
		options[1] = "Accuse";
		options[2] = "Move";
		options[3] = "Hand";
		return options;
	}
	
	public void displayHand() {
		String toPrint = "";
		for(Card c: hand) {
			toPrint += c.toString()+" ";
		}
		System.out.println(toPrint);
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public void setRoll(int roll) {
		this.roll = roll;
		
	}

	public String getName() {
		return name;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Player) {
			Player player = (Player) o;
			return name.equals(player.getName());
		}
		return false;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}
