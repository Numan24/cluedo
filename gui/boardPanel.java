package cluedo.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import cluedo.Board;
import cluedo.Game;
import cluedo.tile.Tile;

public class boardPanel extends JPanel {

	private Game game;
	
	public static final int TILE_SIZE = 30;
	
	/**
	 * Create the panel.
	 */
	public boardPanel(Game game) {
		this.game = game;
		setLayout(new GridLayout(Board.BOARD_HEIGHT, Board.BOARD_LENGTH));
		Tile[][] array = game.getBoardArray();
		
		for(int i = 0; i < Board.BOARD_HEIGHT; i++) {
			for(int j = 0; j < Board.BOARD_LENGTH; j++) {
				JPanel tile = new JPanel(); {
					tile.setPreferredSize(new Dimension(TILE_SIZE,TILE_SIZE));
					tile.setSize(TILE_SIZE, TILE_SIZE);
					tile.setBorder(new LineBorder(Color.BLACK));
					tile.setBackground(array[i][j].getColor());
					tile.setForeground(array[i][j].getColor());
					add(tile);
				}
			}
		}
		
		setBorder(new LineBorder(Color.black));
	}

}
