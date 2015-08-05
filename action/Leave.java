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

	public Leave(Game game, Player player) {
		super(game, player);
		this.room = player.getRoom();
	}

	public void run() {
		displayDoors();
		int door = Input.getInt("Which door would you like to leave from? ");
		DoorTile dt = room.getDoors().get(door-1);
		List<Tile> tiles = adjacentTiles(dt);
		for(Tile t: tiles) {
			if(t instanceof FloorTile) {
				if(t.getPlayer()==null){
					game.getBoard().move(player.getCurrentPosition(), t.getPosition());
					player.setRoom(null);
					room.removePlayer(player);
					t.setPlayer(null);
					return;
				}
			}
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
