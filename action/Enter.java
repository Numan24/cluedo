package cluedo.action;

import cluedo.Game;
import cluedo.Player;
import cluedo.cards.Room;
import cluedo.tile.DoorTile;
import cluedo.tile.RoomTile;

public class Enter extends Action {

	private DoorTile tile;


	public Enter(Game game, Player player, DoorTile tile) {
		super(game, player);
		this.tile = tile;
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

		return player.getRoll() > 0;
	}
}
