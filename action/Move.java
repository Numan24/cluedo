package cluedo.action;

import cluedo.Board;
import cluedo.Game;
import cluedo.Input;
import cluedo.Player;
import cluedo.Position;
import cluedo.tile.*;

public class Move extends Action {

	private Position newPosition;
	private Position oldPosition;
	private Board board;

	public Move(Game game, Player player, Board board) {
		super(game, player);
		this.oldPosition = player.getCurrentPosition();
		this.board = board;
	}

	/**
	 * Asks user for coordinates and tries to set new position to given coords if it
	 * is a valid move.
	 */
	public void run(){
		while(player.getRoll()!=0){
			String direction = Input.getString("Choose a direction to move [N, S, W, E] or X to stop moving.");
			if(direction.equalsIgnoreCase("x")){
				return;
			}
			newPosition = moveDirection(direction);
			while(newPosition==null || !isValidMove()){
					System.out.println("Invalid Move.");
					direction = Input.getString("Choose a direction to move. [N, S, W, E]");
					newPosition = moveDirection(direction);
			}
			board.move(oldPosition, newPosition);
			player.setRoll(player.getRoll()-1);
			oldPosition = newPosition;
			board.redraw();
		}
	}


	public Position moveDirection(String direction){
		direction = direction.toUpperCase();
		switch(direction){
			case  "N":
				return new Position(player.getCurrentPosition().row()-1, player.getCurrentPosition().col());
			case  "S":
				return new Position(player.getCurrentPosition().row()+1, player.getCurrentPosition().col());
			case  "W":
				return new Position(player.getCurrentPosition().row(), player.getCurrentPosition().col()-1);
			case  "E":
				return new Position(player.getCurrentPosition().row(), player.getCurrentPosition().col()+1);
			default:
				System.out.println("Invalid direction.");
		}
		return null;
	}
	

	@Override
	public boolean isValid() {
		for(Tile t : player.adjacentTiles()) {
			if(t instanceof FloorTile) {
				return true;
			}
		}
		return false;
	}

	/**
	 * checks if moving to new position is valid. (Can't move on rooms).
	 * @return boolean. true if valid move, otherwise false.
	 */
	public boolean isValidMove(){
		
		if(newPosition.row()>=game.getBoardArray().length || newPosition.row()<0){
			return false;
		}
		if(newPosition.col()>=game.getBoardArray()[0].length || newPosition.col()<0){
			return false;
		}

		if(!(game.getBoardArray()[newPosition.row()][newPosition.col()] instanceof FloorTile)) {
			return false;
		}

		if(game.getPlayerPositions()[newPosition.row()][newPosition.col()]!=null){
			System.out.println("There is already a player in this position!");
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

	public boolean endsTurn(){
		return true;
	}



}
