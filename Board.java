package cluedo;

import java.io.File;
import java.util.*;

import cluedo.cards.Room;
import cluedo.tile.*;

public class Board {


	
	private List<Player> players = new ArrayList<Player>(); // players in the current game
	

	
	private Tile[][] board; // hard code board please
	private Player[][] playerPositions;
	
	private Game game;
	

	
	/**
	 * Construct a board from a list of players
	 * @param players - list of players in the game
	 */
	public Board(List<Player> players, Game game) {
		this.players = players;
		this.game = game;
		board = createBoard();
		playerPositions = setPlayerPositions();
	}
	
	/**
	 * Creates the board array by reading from the board text file
	 * 
	 * @return the array for the board
	 */
	private Tile[][] createBoard() {
		try {
			Scanner scan = new Scanner(new File("board V2.txt"));
			Tile[][] board = new Tile[25][24];
			int index = 0;
			while(scan.hasNextLine()) {
				String s = scan.nextLine();
				if(s.startsWith("#")){continue;} // skip commented lines
				char[] line = s.toCharArray();
				for(int i = 0; i < line.length; i++) {
					board[index][i] = createTile(line[i], i, index);
				}
				index++;
			}
			scan.close();
			return board;
		}catch(Exception e) {
			System.out.println("Error reading file:  "+ e);
		}
		return null;
	}
	
	
	private Tile createTile(char c, int x, int y) {
		if(c == 'O'){return new FloorTile(x,y);}
		char[] roomID = {'X','S','H','L','D','K','B','C','I','A'};
		for(char room: roomID) {
			if(c == room) {
				List<Room> rooms = game.getRooms();
				for(Room r: rooms) {
					if(c == r.getId()) {
						RoomTile tile = new RoomTile(x,y,r);
						r.addTile(tile);
						return tile;
					}
				}
			}
		}
		char[] doorID = {'Z','Y','W','V','U','T','R','Q','P'};
		for(char door: doorID) {
			if(c == door) {
				List<Room> rooms = game.getRooms();
				for(Room r: rooms) {
					if(c == r.getDoorID()) {
						DoorTile tile = new DoorTile(x,y,r);
						r.addDoor(tile);
						return tile;
					}
				}
			}
		}
		return null;
	}

	public void redraw(){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < getBoard()[0].length; j++){
				if(playerPositions[i][j]==null){
					Tile current = board[i][j];
					if(current instanceof FloorTile) {System.out.print("O");}
					else if(current instanceof RoomTile){System.out.print("X");}
					else if(current instanceof DoorTile) {
						DoorTile dt = (DoorTile) current;
						System.out.print(dt.getRoom().getId());
					}
				} else{System.out.print(players.indexOf(playerPositions[i][j])+1 +"");}
			}
			System.out.print("\n");
		}
	}
		

	public Player[][] setPlayerPositions(){
		Player[][] board = new Player[25][25];
		Position[] positions = {
				new Position(0,7), new Position(0,16), new Position(7,23),
				new Position(17,23), new Position(24,9), new Position(18,0)
		};		
		
		for(int i=0; i < players.size(); i++){
			board[positions[i].row()][positions[i].col()] = players.get(i);
			players.get(i).move(positions[i]);
		}
		return board;
	}
	
	/**
	 * takes two positions and moves player at old position to new position.
	 * @param oldPos & newPos - the positions to move player from.
	 */
	public void move(Position oldPos, Position newPos){
		Player p = playerPositions[oldPos.row()][oldPos.col()];
		playerPositions[newPos.row()][newPos.col()] = p;
		playerPositions[oldPos.row()][oldPos.col()] = null;
		p.move(newPos);
	}
	
	public Player playerAt(Position p){
		return playerPositions[p.row()][p.col()];
	}
	
	public List<Player> getPlayers() {
		return players;
	}

	public Tile[][] getBoard() {
		return board;
	}


	public Player[][] getPlayerPositions() {
		return playerPositions;
	}

	public void setGame(Game game) {
		this.game = game;
		
	}

}
