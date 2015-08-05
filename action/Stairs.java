package cluedo.action;

import cluedo.Game;
import cluedo.Player;
import cluedo.cards.Room;
import cluedo.tile.RoomTile;

public class Stairs extends Action{

	private Room room;

	public Stairs(Game game, Player player,Room room) {
		super(game, player);
		this.room = room;
	}

	public void run() {
		int index = 0;
		RoomTile roomTile = room.getRoomTiles().get(0);
		while(roomTile.getPlayer() != null) {
			index++;
			roomTile = room.getRoomTiles().get(index);
		}
		room.addPlayer(player);
		roomTile.setPlayer(player);
		player.setRoom(room);
		game.getBoard().move(player.getCurrentPosition(), roomTile.getPosition());
		System.out.println("You are now in the "+player.getRoom().getName());
	}
	@Override
	public boolean isValid() {
		return false;
	}

}