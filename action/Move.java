package cluedo.action;

import java.util.Scanner;

import cluedo.Board;
import cluedo.Game;
import cluedo.Player;
import cluedo.Position;

public class Move extends Action {

	private Position newPosition;
	private Position oldPosition;
	
	public Move(Game game, Player player, Position oldPosition, Position newPosition) {
		super(game, player);
		this.newPosition = newPosition;
		this.oldPosition = oldPosition;
	}
	
	public Move(Game game, Player currentPlayer) {
		super(game, currentPlayer);
		setup();
	}


	/**
	 * Asks user for coordinates and tries to set new position to given coords if it
	 * is a valid move.
	 */
	public void setup(){
		Scanner sc = new Scanner(System.in);
		oldPosition = player.getCurrentPosition();
		System.out.println("Choose new coords to move to 'x y'");
		int x = sc.nextInt();
		int y = sc.nextInt();
		newPosition = new Position(y, x);
		while(!player.isValidMove(newPosition)){
				System.out.println("Invalid Move.");
				System.out.println("Choose new coords to move to 'x y'");
				x = sc.nextInt();
				y = sc.nextInt();
				newPosition = new Position(y, x);
		}
	}
	
	/**
	 * checks if moving to new position is valid. (Can't move on rooms).
	 * @return boolean. true if valid move, otherwise false.
	 */
	public boolean isValid(){
		System.out.println("NEW PLAYER POSITION: "+newPosition.toString());
		if(!player.isValidMove(newPosition)){return false;}
		if(game.getBoard()[newPosition.row()][newPosition.col()]=='X'){
			System.out.println("Cannot move on X positions.");
			return false;
		}
		if(game.getPlayerPositions()[newPosition.row()][newPosition.col()]!=null){
			System.out.println("There is already a player in this position!");
		}
		return true;
	}

	public Position getNewPosition() {
		return newPosition;
	}

	public void setNewPosition(Position newPosition) {
		this.newPosition = newPosition;
	}

	public Position getOldPosition() {
		return oldPosition;
	}

	public void setOldPosition(Position oldPosition) {
		this.oldPosition = oldPosition;
	}
	

}
