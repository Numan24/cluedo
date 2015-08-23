package cluedo.action;

import java.util.ArrayList;
import java.util.List;

import cluedo.Game;
import cluedo.Input;
import cluedo.Player;
import cluedo.cards.Room;
import cluedo.tile.DoorTile;
import cluedo.tile.FloorTile;
import cluedo.tile.Tile;

public class Leave extends Action{
	private Room room;
	private DoorTile door;

	public Leave(Game game, Player player, DoorTile door) {
		super(game, player);
		this.room = player.getRoom();
		this.door = door;
	}

	public void run() {
		Move move = new Move(game, player, game.getBoard());
		move.setNewPosition(door.getPosition());
		if(move.isValidMove()){
			game.movePlayer(player, player.getCurrentPosition(), door.getPosition());
			player.setRoom(null);
			room.removePlayer(player);
		}
	}

	private void displayDoors() {
		String toPrint = "";
		for(int i = 1; i <= room.getDoors().size(); i++) {
			toPrint += "[Door "+i+"]";
		}
		System.out.println(toPrint);
	}

	public List<Tile> adjacentTiles(DoorTile dt) {
		Tile[][] board = game.getBoardArray();
		List<Tile> tiles = new ArrayList<Tile>();
		int x = dt.getPosition().col();
		int y = dt.getPosition().row();
		if(x < 23){tiles.add(board[y][x+1]);}
		if(x > 0){tiles.add(board[y][x-1]);}
		if(y < 24){tiles.add(board[y+1][x]);}
		if(y > 0){tiles.add(board[y-1][x]);}
		return tiles;
	}

	@Override
	public boolean isValid() {
		if(player.getRoom() == null){return false;}
		return true;
	}

	public boolean endsTurn(){
		return false;
	}

}
