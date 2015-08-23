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
import cluedo.cards.Weapon;


public class WeaponSelect extends JPanel implements ActionListener{

	private static final long serialVersionUID = -5878345777929474786L;

	//list of available weapons in the game
	private List<String> weapons;
	
	//picture for the currently selected weapon
	private JLabel weaponPicture;
	
	//currently selected weapon
	private String selectedWeapon;
	
	public WeaponSelect(Game game) {
		super(new BorderLayout());
		weapons = new ArrayList<String>();
		
		//get all weapons
		for(Weapon w : game.getWeapons()) {
			weapons.add(w.getName());
		}
		
		//create buttons for each weapon
		JRadioButton[] buttons = new JRadioButton[weapons.size()];
		for(int i = 0; i < buttons.length; i++) {
			buttons[i] = new JRadioButton(weapons.get(i));
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
		weaponPicture = new JLabel(createImageIcon(weapons.get(0)+".png"));
		
		//add buttons to button panel
		JPanel buttonPanel = new JPanel(new GridLayout(0,1));
		for(JRadioButton b : buttons) {
			buttonPanel.add(b);
		}
		
		//add all components to 'this'
		add(buttonPanel, BorderLayout.LINE_START);
		add(weaponPicture, BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//update selected weapon and image
		selectedWeapon = e.getActionCommand();
		weaponPicture.setIcon(createImageIcon(e.getActionCommand()+".png"));
		
		
	}
	
	/**
	 * create imageIcon for the weapons
	 * @param path
	 * @return
	 */
	private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Game.class.getResource("images/cards/"+path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

	/**
	 * @return the selectedWeapon
	 */
	public String getSelectedWeapon() {
		return selectedWeapon;
	}
}
