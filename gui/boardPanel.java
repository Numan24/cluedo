package cluedo.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import cluedo.Board;
import cluedo.Game;
import cluedo.Player;
import cluedo.Position;
import cluedo.tile.DoorTile;
import cluedo.tile.FloorTile;
import cluedo.tile.RoomTile;
import cluedo.tile.Tile;

public class BoardPanel extends JPanel{


	private static final long serialVersionUID = 8198083347466113567L;

	private Game game;
	
	public static final int TILE_SIZE = 30;
	private Tile[][] board;
	private JLabel[][] labels;
	//private final ImageIcon floor = new ImageIcon("floor.png");
	//private final ImageIcon room = new ImageIcon("room.jpg");
	//private final ImageIcon door = new ImageIcon("door.jpg");

	
	/**
	 * Create the panel.
	 */
	public BoardPanel(Game game) {
		this.game = game;
		setLayout(new GridLayout(Board.BOARD_HEIGHT, Board.BOARD_LENGTH));
		board = game.getBoardArray();
		labels = new JLabel[Board.BOARD_HEIGHT][Board.BOARD_LENGTH];
		for(int i = 0; i < Board.BOARD_HEIGHT; i++) {
			for(int j = 0; j < Board.BOARD_LENGTH; j++) {
				JLabel tile = new JLabel(); {
					tile.setPreferredSize(new Dimension(TILE_SIZE,TILE_SIZE));
					tile.setBackground(board[i][j].getColor());
					tile.setOpaque(true);
					if(board[i][j].getPlayer() != null) {
						tile.setIcon(board[i][j].getPlayer().getIcon());
						//tile.setOpaque(false);
					}
					if(board[i][j] instanceof RoomTile) {
						calculateBorder(tile, i, j);
					}
					else {tile.setBorder(new LineBorder(Color.BLACK));}
					add(tile);
					labels[i][j] = tile;
				}
			}
		}
		setBorder(new LineBorder(Color.black));
		setMouseover();
		
	}

	
	/**
	 * Sets text for mousing over tiles on board. 
	 * Prioritises player names followed by room name.
	 */
	private void setMouseover() {
		for(int i =0; i < labels.length; i++){
			for(int j = 0; j < labels[i].length; j++){
				if(game.getPlayerPositions()[i][j]!=null){
					labels[i][j].setToolTipText(game.getPlayerPositions()[i][j].getName());
				}
				else if(board[i][j] instanceof RoomTile){
					RoomTile r = (RoomTile) board[i][j];
					labels[i][j].setToolTipText(r.getRoom().getName());
				}
				else{labels[i][j].setToolTipText(null);}
			}
		}
		
	}

	private void calculateBorder(JLabel tile, int y, int x) {
		Tile[][] board  = game.getBoardArray();
		int top = 0;
		int right = 0;
		int bottom = 0;
		int left = 0;
		if(x < 23){
			if(board[y][x+1] instanceof FloorTile) {
				right = 1;
			}
		} else{right = 1;}
		if(x > 0){
			if(board[y][x-1] instanceof FloorTile) {
				left = 1;
			}
		} else{left = 1;}
		if(y < 24){
			if(board[y+1][x] instanceof FloorTile) {
				bottom = 1;
			}
		} else {bottom = 1;}
		if(y > 0){
			if(board[y-1][x] instanceof FloorTile) {
				top = 1;
			}
		}else {top = 1;}
		tile.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.black));
	}


	public void movePlayer(Player player, Position oldPos, Position newPos) {
		labels[oldPos.row()][oldPos.col()].setIcon(null);
		labels[newPos.row()][newPos.col()].setIcon(player.getIcon());
		setMouseover();
	}
	
	public Tile checkMouseOnDoor(int x, int y){
		for(int i = 0; i < Board.BOARD_HEIGHT; i++) {
			for(int j = 0; j < Board.BOARD_LENGTH; j++) {
				if(labels[i][j].getBounds().contains(x,y)){
					if(board[i][j] instanceof DoorTile){
						return board[i][j];
					}
				}
			}
		}
		return null;
	}
	
	public void removePlayer(Player player){
		labels[player.getCurrentPosition().row()][player.getCurrentPosition().col()].setIcon(null);
		setMouseover();
	}
	
	
	
}
