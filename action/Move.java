package cluedo.action;

import cluedo.Board;
import cluedo.Player;
import cluedo.Position;

public class Move extends Action {

	Player player;
	Position newPosition;
	Position oldPosition;
	
	public Move(Board board, Player player, Position oldPosition, Position newPosition) {
		super(board, player);
		this.newPosition = newPosition;
		this.oldPosition = oldPosition;
	}
	
	public boolean isValid(){
		if(!player.isValidMove(newPosition)){return false;}
		
		return false;
	}

}
