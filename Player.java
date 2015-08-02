package cluedo;

import java.util.*;

import cluedo.cards.Card;
import cluedo.cards.Room;
import cluedo.tile.*;

public class Player {

	private String name;
	private int roll;
	private List<Card> hand = new ArrayList<Card>();
	private Position currentPosition;
	private Room room;
	private DoorTile lastDoorEntered;
	private final Game game;

	public Player(String name, Game game) {
		this.name = name;
		this.game = game;
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
	
	/**
	 * checks player has rolled high enough number to move as many steps as new position is away.
	 * @param newPos - position to try move to
	 * @return boolean - where it is a valid move
	 */
	public boolean isValidMove(Position newPos){
		if(Math.abs(newPos.row() - currentPosition.row()) + Math.abs(newPos.col()-currentPosition.col()) > roll){
			return false;
		}
		return true;
	}

	/**
	 * get the current options that the player has at their given position.
	 * @return array of options
	 */
	public List<String> getOptions() {
		List<String> opts = new ArrayList<String>();
		List<Tile> adjacent = adjacentTiles();
		opts.add("Accuse");
		opts.add("Hand");
		if(room==null){
			opts.add("Move");
		}
		else {
			opts.add("Leave");
			opts.add("Guess");
			if(room.getConnectedTo() != null) {
				opts.add("Stairs: "+room.getConnectedTo().getName());
			}
		}
		
		for(Tile t: adjacent) {
			if(t instanceof DoorTile) {
				DoorTile dt = (DoorTile) t;
				opts.add("Enter: "+dt.getRoom().getName());
				}
		}
		return opts;
	}
	
	public List<Tile> adjacentTiles() {
		Tile[][] board = game.getBoard();
		int x = currentPosition.col();
		int y = currentPosition.row();
		List<Tile> tiles = new ArrayList<Tile>();
		if(x < 23){tiles.add(board[y][x+1]);}
		if(x > 0){tiles.add(board[y][x-1]);}
		if(y < 24){tiles.add(board[y+1][x]);}
		if(y > 0){tiles.add(board[y-1][x]);}
		return tiles;
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

	
	public int getRoll(){
		return this.roll;
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

	public DoorTile getLastDoorEntered() {
		return lastDoorEntered;
	}

	public void setLastDoorEntered(DoorTile lastDoorEntered) {
		this.lastDoorEntered = lastDoorEntered;
	}

}
