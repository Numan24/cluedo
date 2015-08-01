package cluedo;

import java.io.File;
import java.util.*;

public class Board {


	
	private List<Player> players = new ArrayList<Player>(); // players in the current game
	

	
	private char[][] board; // hard code board please
	private Player[][] playerPositions;
	

	
	/**
	 * Construct a board from a list of players
	 * @param players - list of players in the game
	 */
	public Board(List<Player> players) {
		this.players = players;
		
		setBoard(createBoard());
		playerPositions = setPlayerPositions();
		

		setPlayerPositions();
	}
	
	/**
	 * Creates the board array by reading from the board text file
	 * 
	 * @return the array for the board
	 */
	private char[][] createBoard() {
		try {
			Scanner scan = new Scanner(new File("board.txt"));
			char[][] board = new char[25][25];
			int index = 0;
			while(scan.hasNextLine()) {
				String s = scan.nextLine();
				if(s.startsWith("#")){continue;} // skip commented lines
				char[] line = s.toCharArray();
				board[index] = line;
				index++;
			}
			scan.close();
			return board;
		}catch(Exception e) {
			System.out.println("Error reading file:  "+ e);
		}
		return null;
	}
	
	
	public void redraw(){
		for(int i = 0; i < getBoard().length; i++){
			for(int j = 0; j < getBoard()[0].length; j++){
				if(playerPositions[i][j]==null){
					System.out.print(getBoard()[i][j]+" ");
				} else{System.out.print(players.indexOf(playerPositions[i][j])+1 +" ");}
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

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	public Player[][] getPlayerPositions() {
		return playerPositions;
	}

}
