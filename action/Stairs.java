package cluedo.action;

import cluedo.Game;
import cluedo.Player;
import cluedo.cards.Room;
import cluedo.tile.RoomTile;

public class Stairs extends Action{
	
	/*
	 * created when using the stairs in a room
	 */

	private Room room; // room to move to

	public Stairs(Game game, Player player) {
		super(game, player);
		this.room = player.getRoom().getConnectedTo();
	}

	public void run() {
		int index = 0;
		RoomTile roomTile = room.getRoomTiles().get(0);
		// get a tile in the room that doesn't have a player on it
		while(roomTile.getPlayer() != null) {
			index++;
			roomTile = room.getRoomTiles().get(index);
		}
		//update player, room and room tiles
		//player.getRoom().removePlayer(player);
		room.addPlayer(player);
		roomTile.setPlayer(player);
		player.setRoom(room);
		
		
		game.getBoard().move(player.getCurrentPosition(), roomTile.getPosition());
		System.out.println("You are now in the "+player.getRoom().getName());
		game.getBoard().redraw();
	}
	
	
	@Override
	public boolean isValid() {
		return room != null && room.getConnectedTo() != null;
	}

	public boolean endsTurn(){
		return false;
	}

}