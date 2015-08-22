package cluedo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import cluedo.cards.Card;
import cluedo.cards.Room;
import cluedo.tile.DoorTile;
import cluedo.tile.Tile;

public class Player {
	
	/*
	 * Represents a single user(player) in the game. 
	 */

	private String name;
	private List<Card> hand = new ArrayList<Card>();
	private Position currentPosition;
	private Room room;
	private final Game game;
	private boolean hasLost;
	private Color color;
	
	private ImageIcon icon;
	
	private int roll; // the dice roll the player got on their turn

	public Player(String name, Game game, String icon) {
		this.name = name;
		this.game = game;
		this.hasLost = false;
		this.icon = createImageIcon(icon);
		Random rand = new Random();
		this.color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
	}
	
	private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Game.class.getResource("player-"+path+".png");
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

	/**
	 * get the current options that are available to the player.
	 * @return array of options
	 */
	public List<String> getOptions() {
		List<String> opts = new ArrayList<String>();
		List<Tile> adjacent = adjacentTiles();
		opts.add("Accuse");
		opts.add("Hand");
		if(room==null && roll != 0){
			opts.add("Move");
		}
		if(room != null) {
			opts.add("Guess");
			if(roll != 0){
				opts.add("Leave");
				if(room.getConnectedTo() != null) {
					opts.add("Stairs: "+room.getConnectedTo().getName());
				}
			}
		}
		if(roll != 0 && room==null) {
			for(Tile t: adjacent) {
				if(t instanceof DoorTile) {
					DoorTile dt = (DoorTile) t;
					opts.add("Enter: "+dt.getRoom().getName());
					}
			}
		}
		if(roll == 0){
			opts.add("End");
		}
		return opts;
	}

	/**
	 * returns the tiles that are adjacent to the player
	 * not including the tiles that are at a diagonal to the player
	 * @return
	 */
	public List<Tile> adjacentTiles() {
		Tile[][] board = game.getBoardArray();
		int x = currentPosition.col();
		int y = currentPosition.row();
		List<Tile> tiles = new ArrayList<Tile>();
		if(x < 23){tiles.add(board[y][x+1]);}
		if(x > 0){tiles.add(board[y][x-1]);}
		if(y < 24){tiles.add(board[y+1][x]);}
		if(y > 0){tiles.add(board[y-1][x]);}
		return tiles;
	}

	/**
	 * prints out the hand of the player
	 */
	public void displayHand() {
		String toPrint = "";
		for(Card c: hand) {
			toPrint += c.toString()+" ";
		}
		System.out.println(toPrint);
	}
	public boolean equals(Object o) {
		if(o instanceof Player) {
			Player player = (Player) o;
			return name.equals(player.getName());
		}
		return false;
	}
	
	public int roll(){
		Random rand = new Random();
		roll = rand.nextInt(6)+1;
		return roll;
	}
	
	/*
	 * Getters and Setters
	 */

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

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
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

	public boolean hasLost() {
		return hasLost;
	}

	public void lost(boolean b) {
		hasLost = b;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public ImageIcon getIcon(){
		return icon;
	}

}
