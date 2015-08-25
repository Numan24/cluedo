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
import cluedo.cards.Room;

/**
 * panel that is used in a guess or accuse to display the rooms
 *
 */
public class RoomSelect extends JPanel implements ActionListener{

	private static final long serialVersionUID = 7779188616359149755L;

	private List<String> rooms;
	
	private JLabel roomPicture;
	
	private String selectedRoom;
	
	public RoomSelect(Game game) {
		super(new BorderLayout());
		rooms = new ArrayList<String>();
		//get all different rooms from the game
		for(Room r : game.getRooms()) {
			if(r.getName().equals("Swimming Pool")){continue;}
			rooms.add(r.getName());
		}
		//create a button for each room
		JRadioButton[] buttons = new JRadioButton[rooms.size()];
		for(int i = 0; i < buttons.length; i++) {
			buttons[i] = new JRadioButton(rooms.get(i));
		}
		buttons[0].setSelected(true);
		//add action listener to each button
		for(JRadioButton b : buttons) {
			b.addActionListener(this);
		}
		//add buttons to a group
		ButtonGroup bg = new ButtonGroup();
		for(int i = 0; i < buttons.length; i++) {
			bg.add(buttons[i]);
		}
		//set initial room picture
		roomPicture = new JLabel(createImageIcon(rooms.get(0)+".png"));
		//add buttons to panel
		JPanel buttonPanel = new JPanel(new GridLayout(0,1));
		for(JRadioButton b : buttons) {
			buttonPanel.add(b);
		}
		add(buttonPanel, BorderLayout.LINE_START);
		add(roomPicture, BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		selectedRoom = e.getActionCommand();
		roomPicture.setIcon(createImageIcon(e.getActionCommand()+".png"));
		
		
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
	 * @return the selectedWeapon
	 */
	public String getSelectedRoom() {
		return selectedRoom;
	}
}

