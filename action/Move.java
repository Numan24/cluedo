package cluedo.action;

import java.util.Scanner;

import cluedo.Board;
import cluedo.Player;
import cluedo.Position;

public class Move extends Action {

	private Position newPosition;
	private Position oldPosition;
	
	public Move(Board board, Player player, Position oldPosition, Position newPosition) {
		super(board, player);
		this.newPosition = newPosition;
		this.oldPosition = oldPosition;
	}
	
	public Move(Board board, Player currentPlayer) {
		super(board, currentPlayer);
		setup();
	}

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
	
	public boolean isValid(){
		System.out.println("NEW PLAYER POSITION: "+newPosition.toString());
		if(!player.isValidMove(newPosition)){return false;}
		if(board.getBoard()[newPosition.row()][newPosition.col()]=='X'){
			System.out.println("Cannot move on X positions.");
			return false;
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
