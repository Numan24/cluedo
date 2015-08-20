package cluedo.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import cluedo.Board;
import cluedo.Game;
import cluedo.Player;
import cluedo.tile.DoorTile;
import cluedo.tile.FloorTile;
import cluedo.tile.RoomTile;
import cluedo.tile.Tile;

public class boardPanel extends JPanel {

	private Game game;
	
	public static final int TILE_SIZE = 30;
	private Tile[][] board;
	private Player[][] players;
	private final ImageIcon floor = new ImageIcon("floor.png");
	private final ImageIcon room = new ImageIcon("room.jpg");
	private final ImageIcon door = new ImageIcon("door.jpg");

	
	/**
	 * Create the panel.
	 */
	public boardPanel(Game game) {
		this.game = game;
		setLayout(new GridLayout(Board.BOARD_HEIGHT, Board.BOARD_LENGTH));
		board = game.getBoardArray();
		
		for(int i = 0; i < Board.BOARD_HEIGHT; i++) {
			for(int j = 0; j < Board.BOARD_LENGTH; j++) {
				JPanel tile = new JPanel(); {
					tile.setPreferredSize(new Dimension(TILE_SIZE,TILE_SIZE));
					tile.setSize(TILE_SIZE, TILE_SIZE);
					tile.setBorder(new LineBorder(Color.BLACK));
					tile.setBackground(board[i][j].getColor());
					tile.setForeground(board[i][j].getColor());
					add(tile);
				}
			}
		}
		
		setBorder(new LineBorder(Color.black));
	}
	
	

	public void paint(Graphics g){

		players = game.getPlayerPositions();
		int x = 0;
		int y = 0;
		
		g.setColor(Color.WHITE);
		g.fillRect(x, y, board[0].length*TILE_SIZE, board.length*TILE_SIZE);
		
		// THE BOARD TILES
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if(board[i][j] instanceof FloorTile){
					g.setColor(Color.GRAY);
					floor.paintIcon(this, g, x, y);
				} 
				else if(board[i][j] instanceof DoorTile){
					door.paintIcon(this, g, x, y);
				}
				else{					
					room.paintIcon(this, g, x, y);
				}
				
				if(players[i][j]!=null){
					g.setColor(players[i][j].getColor());
					g.fillOval(x, y, TILE_SIZE, TILE_SIZE);
					g.setColor(Color.BLACK);
					g.drawOval(x,y, TILE_SIZE, TILE_SIZE);
				}
				
				x+=TILE_SIZE;
			}
			x=0;
			y+=TILE_SIZE;
		}
		
		
		// Did I really do all this just for outlines
		x=0;
		y=0;
		g.setColor(Color.BLACK);
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if(board[i][j] instanceof RoomTile){
					if(i+1!=board.length){
						if(board[i+1][j] instanceof FloorTile){							
							g.drawLine(x, y+TILE_SIZE, x+TILE_SIZE, y+TILE_SIZE);
						}
					}
					
					if(i-1>0){
						if(board[i-1][j] instanceof FloorTile){
							g.drawLine(x, y, x+TILE_SIZE, y);
						}
					}
					
					if(j+1!=board[0].length){
					if(board[i][j+1] instanceof FloorTile){
						g.drawLine(x+TILE_SIZE, y, x+TILE_SIZE, y+TILE_SIZE);
					}
					}
					
					if(j-1>0){
						if(board[i][j-1] instanceof FloorTile){
							g.drawLine(x, y, x, y+TILE_SIZE);
						}
					}
				}
				x+=TILE_SIZE;
			}
			x=0;
			y+=TILE_SIZE;
		}

		
		g.drawRect(0, 0, board[0].length*TILE_SIZE, board.length*TILE_SIZE);
	}

}
