package cluedo.action;

import java.util.List;

import cluedo.Game;
import cluedo.Player;
import cluedo.cards.Room;
import cluedo.tile.DoorTile;
import cluedo.tile.RoomTile;
import cluedo.tile.Tile;

public class Enter extends Action {

	private DoorTile tile;


	public Enter(Game game, Player player) {
		super(game, player);
		List<Tile> tiles = player.adjacentTiles();
		for(Tile t : tiles) {
			if(t instanceof DoorTile) {
				DoorTile dt = (DoorTile) t;
				this.tile = dt;
			}
		}
	}

	public void run() {
		Room room = tile.getRoom();
		int index = 0;
		RoomTile roomTile = room.getRoomTiles().get(0);
		while(roomTile.getPlayer() != null) {
			index++;
			roomTile = room.getRoomTiles().get(index);
		}
		room.addPlayer(player);
		roomTile.setPlayer(player);
		player.setRoom(tile.getRoom());
		player.setLastDoorEntered(tile);
		game.getBoard().move(player.getCurrentPosition(), roomTile.getPosition());
		System.out.println("You are now in the "+player.getRoom().getName());
		player.setRoll(0);
	}


	public boolean isValid() {
		List<Tile> tiles = player.adjacentTiles();
		for(Tile t : tiles) {
			if(t instanceof DoorTile) {
				return true;
			}
		}
		if(player.getRoll() > 0) return true;
		return false;
	}
	public boolean endsTurn(){
		return false;
	}
}
