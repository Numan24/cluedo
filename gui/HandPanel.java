package cluedo.gui;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cluedo.Game;
import cluedo.cards.Card;

/**
 *panel that displays the hand of the current player
 *
 */
public class HandPanel extends JPanel {
	
	private static final long serialVersionUID = -6086777099635929213L;
	
	private Game game;
	private List<JLabel> cardLabels; 

	/**
	 * Create the panel.
	 */
	public HandPanel(Game game) {
		this.game = game;
		cardLabels = new ArrayList<JLabel>();
		setLayout(new GridLayout(1, 0, 0, 0));
		setToolTipText("Current players hand");
		List<Card> hand = game.getCurrentPlayer().getHand();
		for(Card c : hand) {
			ImageIcon card = c.getIcon();
			
			JLabel label = new JLabel(card);
			cardLabels.add(label);
			add(label);
		}
	}
	
	/**
	 * update the icon on the labels when a player has moved
	 */
	public void updateLabels() {
		List<Card> newCards = game.getCurrentPlayer().getHand();
		for(int i = 0; i < cardLabels.size(); i++) {
			if(i >= newCards.size()){
				cardLabels.get(i).setIcon(null);
			}
			else {
				cardLabels.get(i).setIcon(newCards.get(i).getIcon());
			}
		}
	}
	
}
