package cluedo.gui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cluedo.Game;
import cluedo.cards.Card;

public class HandPanel extends JPanel {
	
	private Game game;

	/**
	 * Create the panel.
	 */
	public HandPanel(Game game) {
		this.game = game;
		
		setLayout(new GridLayout(1, 0, 0, 0));
		setToolTipText("Current players hand");
		
		List<Card> hand = game.getCurrentPlayer().getHand();
		ImageIcon card = makeImageIcon("card.png");
		for(Card c : hand) {
			JLabel label = new JLabel(card);
			add(label);
		}
	}
	
	private static ImageIcon makeImageIcon(String filename) {		
		java.net.URL imageURL = Game.class.getResource(filename);

		ImageIcon icon = null;
		if (imageURL != null) {
			icon = new ImageIcon(imageURL);
		}
		return icon;
	}

	
}
