package cluedo.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import cluedo.Game;

public class CharacterSelect extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = -2930990384432294892L;
	
	private static String missScarlett = "Miss Scarlett";
	private static String colonelMustard = "Colonel Mustard";
	private static String mrsWhite = "Mrs. White";
	private static String reverendGreen = "The Reverend Green";
	private static String mrsPeacock = "Mrs Peacock";
	private static String professorPlum = "Professor Plum";
	
	private JLabel characterPic;
	
	private String selectedChar ="Miss Scarlett";
	
	
	public CharacterSelect() {
		super(new BorderLayout());
		
		
		//create radio buttons
		JRadioButton missScarlettButton = new JRadioButton(missScarlett);
		missScarlettButton.setActionCommand(missScarlett);
		missScarlettButton.setSelected(true);
		
		JRadioButton colonelMustardButton = new JRadioButton(colonelMustard);
		colonelMustardButton.setActionCommand(colonelMustard);
		
		JRadioButton mrsWhiteButton = new JRadioButton(mrsWhite);
		mrsWhiteButton.setActionCommand(mrsWhite);
		
		JRadioButton reverendGreenButton = new JRadioButton(reverendGreen);
		reverendGreenButton.setActionCommand(reverendGreen);
		
		JRadioButton mrsPeacockButton = new JRadioButton(mrsPeacock);
		mrsPeacockButton.setActionCommand(mrsPeacock);
		
		JRadioButton professorPlumButton = new JRadioButton(professorPlum);
		professorPlumButton.setActionCommand(professorPlum);
		
		// add buttons to group
		ButtonGroup bg = new ButtonGroup();
		bg.add(missScarlettButton);
		bg.add(colonelMustardButton);
		bg.add(mrsWhiteButton);
		bg.add(reverendGreenButton);
		bg.add(mrsPeacockButton);
		bg.add(professorPlumButton);
		
		// add action listeners to buttons
		missScarlettButton.addActionListener(this);
		colonelMustardButton.addActionListener(this);
		mrsWhiteButton.addActionListener(this);
		reverendGreenButton.addActionListener(this);
		mrsPeacockButton.addActionListener(this);
		professorPlumButton.addActionListener(this);
		
		characterPic = new JLabel(createImageIcon(missScarlett+".png"));
		
		JPanel buttonPanel = new JPanel(new GridLayout(0,1));
		buttonPanel.add(missScarlettButton);
		buttonPanel.add(colonelMustardButton);
		buttonPanel.add(mrsWhiteButton);
		buttonPanel.add(reverendGreenButton);
		buttonPanel.add(mrsPeacockButton);
		buttonPanel.add(professorPlumButton);
		add(buttonPanel, BorderLayout.LINE_START);
		add(characterPic, BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		characterPic.setIcon(createImageIcon(e.getActionCommand()+".png"));
		selectedChar = e.getActionCommand();
		
	}
	
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
	 * @return the selectedChar
	 */
	public String getSelectedChar() {
		return selectedChar;
	}

}
