package cluedo.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import cluedo.Game;

public class CharacterSelect extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = -2930990384432294892L;
	
	private JLabel characterPicture;
	private String selectedChar ="Miss Scarlett";
	private List<String> characters;
	
	
	public CharacterSelect(Game game) {
		super(new BorderLayout());
		characters = new ArrayList<String>();
		for(cluedo.cards.Character c : game.getCharacters()) {
			characters.add(c.getName());
		}
		//create buttons for each weapon
		JRadioButton[] buttons = new JRadioButton[characters.size()];
		for(int i = 0; i < buttons.length; i++) {
			buttons[i] = new JRadioButton(characters.get(i));
		}
		//add buttons to a group
		ButtonGroup bg = new ButtonGroup();
		for(int i = 0; i < buttons.length; i++) {
			bg.add(buttons[i]);
		}
		//add action listener to each button
		for(JRadioButton b : buttons) {
			b.addActionListener(this);
		}
		//set first button to selected and update image
		buttons[0].setSelected(true);
		characterPicture = new JLabel(createImageIcon(characters.get(0)+".png"));
		//add buttons to button panel
		JPanel buttonPanel = new JPanel(new GridLayout(0,1));
		for(JRadioButton b : buttons) {
			buttonPanel.add(b);
		}
		//add all components to 'this'
		add(buttonPanel, BorderLayout.LINE_START);
		add(characterPicture, BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		characterPicture.setIcon(createImageIcon(e.getActionCommand()+".png"));
		selectedChar = e.getActionCommand();
		
	}
	
	private ImageIcon createImageIcon(String path) {
		path = path.toLowerCase();
        java.net.URL imgURL = Game.class.getResource("images/cards/"+path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }


	/**
	 * @return the selectedChar
	 */
	public String getSelectedChar() {
		return selectedChar;
	}

}
