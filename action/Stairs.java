package cluedo.action;

import cluedo.Game;
import cluedo.Output;
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
		if(player.getRoom() == null) {return;}
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
		room.addPlayer(player);
		roomTile.setPlayer(player);
		player.setRoom(room);
		
		game.movePlayer(player, player.getCurrentPosition(), roomTile.getPosition());
		
		
		Output.appendText("You are now in the "+player.getRoom().getName());
		
	}
	
	
	@Override
	public boolean isValid() {
		return room != null;
	}

	public boolean endsTurn(){
		return false;
	}

}