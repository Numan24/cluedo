package cluedo.action;

import java.util.List;

import cluedo.Game;
import cluedo.Output;
import cluedo.Player;
import cluedo.cards.Room;
import cluedo.tile.DoorTile;
import cluedo.tile.RoomTile;
import cluedo.tile.Tile;

public class Enter extends Action {

	private DoorTile tile; // the tile of the door to be entered
	private RoomTile roomTile;

	public Enter(Game game, Player player) {
		super(game, player);
		tile =  (DoorTile) game.getBoardArray()[player.getCurrentPosition().row()][player.getCurrentPosition().col()];
	}

	public void run() {
		Room room = tile.getRoom(); // get the room the door is connected to
		int index = 0;
		roomTile = room.getRoomTiles().get(0);
		// select a tile in the room that doesn't have a player on it
		while(roomTile.getPlayer() != null) {
			index++;
			roomTile = room.getRoomTiles().get(index);
		}
		
		//update player, room and room tiles
		room.addPlayer(player);
		roomTile.setPlayer(player); 
		player.setRoom(tile.getRoom()); 
		game.movePlayer(player, player.getCurrentPosition(), roomTile.getPosition());
		
		Output.appendText("You are now in the "+player.getRoom().getName());
		player.setRoll(0); // turn is over after entering a room so the roll is now 0
	}

/**
 * Returns if the current player can enter a room.
 * The player can enter a room if there is a floor tile adjacent to them and their roll is greater than 0.
 */
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
	
	public RoomTile getRoomTile() {
		return roomTile;
	}
	
	public boolean endsTurn(){
		return false;
	}
}
