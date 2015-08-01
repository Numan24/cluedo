package cluedo.action;

import java.util.Scanner;

import cluedo.Board;
import cluedo.Player;
import cluedo.Position;

public class Move extends Action {

	Position newPosition;
	Position oldPosition;
	
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
		newPosition = new Position(sc.nextInt(), sc.nextInt());
		while(!player.isValidMove(newPosition)){
				System.out.println("Invalid Move.");
				System.out.println("Choose new coords to move to 'x y'");
				newPosition = new Position(sc.nextInt(), sc.nextInt());
		}
	}
	
	public boolean isValid(){
		if(!player.isValidMove(newPosition)){return false;}
		// FINISH THIS.
		return true;
	}

}
