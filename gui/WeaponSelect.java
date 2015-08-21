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
	
	private List<String> weapons;
	
	private JLabel weaponPicture;
	
	private Game game;

	private String selectedWeapon;
	
	public WeaponSelect(Game game) {
		super(new BorderLayout());
		this.game = game;
		weapons = new ArrayList<String>();
		for(Weapon w : game.getWeapons()) {
			weapons.add(w.getName());
		}
		
		JRadioButton[] buttons = new JRadioButton[weapons.size()];
		for(int i = 0; i < buttons.length; i++) {
			buttons[i] = new JRadioButton(weapons.get(i));
		}
		
		ButtonGroup bg = new ButtonGroup();
		for(int i = 0; i < buttons.length; i++) {
			bg.add(buttons[i]);
		}
		for(JRadioButton b : buttons) {
			b.addActionListener(this);
		}
		
		weaponPicture = new JLabel(createImageIcon("card.png"));
		
		JPanel buttonPanel = new JPanel(new GridLayout(0,1));
		for(JRadioButton b : buttons) {
			buttonPanel.add(b);
		}
		add(buttonPanel, BorderLayout.LINE_START);
		add(weaponPicture, BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		weaponPicture.setIcon(createImageIcon("card.png"));
		selectedWeapon = e.getActionCommand();
		
	}
	
	private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Game.class.getResource(path);
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
