package cluedo.action;

import cluedo.Game;
import cluedo.Output;
import cluedo.Player;
import cluedo.Position;
import cluedo.tile.DoorTile;
import cluedo.tile.FloorTile;
import cluedo.tile.RoomTile;
import cluedo.tile.Tile;

public class Move extends Action {

	private Position newPosition;
	private Position oldPosition;

	public Move(Game game, Player player) {
		super(game, player);
		this.oldPosition = player.getCurrentPosition();
	}

	/**
	 * Asks user for coordinates and tries to set new position to given coords if it
	 * is a valid move.
	 */
	public void run(){
		
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
		
		if(game.getBoardArray()[newPosition.row()][newPosition.col()] instanceof FloorTile){
			if(game.getBoardArray()[oldPosition.row()][oldPosition.col()] instanceof RoomTile){
				Output.appendText("Must leave room via door!");
				return false;
			}
		}

		if(!((game.getBoardArray()[newPosition.row()][newPosition.col()] instanceof FloorTile) ||(game.getBoardArray()[newPosition.row()][newPosition.col()] instanceof DoorTile))) {
			return false;
		}

		if(game.getPlayerPositions()[newPosition.row()][newPosition.col()]!=null){
			Output.appendText("There is already a player in this position!");
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
		return false;
	}



}
