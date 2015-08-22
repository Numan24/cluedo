package cluedo.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import cluedo.Game;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class OptionsPanel extends JPanel {
	
	private Game game;
	private JButton rollDice;
	private JButton guess;
	private JButton accuse;
	private JButton endTurn;
	private JTextArea textArea;


	private Frame frame;
	private JButton btnStairs;
	private JScrollPane scrollPane;


	/**
	 * Create the panel.
	 */
	public OptionsPanel(Frame frame, Game game) {
		this.frame = frame;
		setBorder(new TitledBorder(null, "Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		rollDice = new JButton("Roll Dice");
		GridBagConstraints gbc_btnRollDice = new GridBagConstraints();
		gbc_btnRollDice.fill = GridBagConstraints.BOTH;
		gbc_btnRollDice.anchor = GridBagConstraints.WEST;
		gbc_btnRollDice.insets = new Insets(0, 0, 5, 5);
		gbc_btnRollDice.gridx = 0;
		gbc_btnRollDice.gridy = 0;
		add(rollDice, gbc_btnRollDice);
		
		rollDice.addActionListener(new ActionListener()
	    {
		      public void actionPerformed(ActionEvent e)
		      {
		    	  rollDice.setEnabled(false);
		    	  frame.buttonPressed(e);
		      }
		    });
		
		
		guess = new JButton("Guess");
		GridBagConstraints gbc_btnGuess = new GridBagConstraints();
		gbc_btnGuess.fill = GridBagConstraints.BOTH;
		gbc_btnGuess.anchor = GridBagConstraints.WEST;
		gbc_btnGuess.insets = new Insets(0, 0, 5, 0);
		gbc_btnGuess.gridx = 1;
		gbc_btnGuess.gridy = 0;
		add(guess, gbc_btnGuess);
		
		guess.addActionListener(new ActionListener()
	    {
		      public void actionPerformed(ActionEvent e)
		      {
		    	  frame.buttonPressed(e);
		      }
		    });
		
		
		accuse = new JButton("Accuse");
		GridBagConstraints gbc_btnAccuse = new GridBagConstraints();
		gbc_btnAccuse.fill = GridBagConstraints.BOTH;
		gbc_btnAccuse.anchor = GridBagConstraints.WEST;
		gbc_btnAccuse.insets = new Insets(0, 0, 5, 0);
		gbc_btnAccuse.gridx = 1;
		gbc_btnAccuse.gridy = 1;
		
		accuse.addActionListener(new ActionListener()
	    {
		      public void actionPerformed(ActionEvent e)
		      {
		    	  frame.buttonPressed(e);
		      }
		    });
		
		btnStairs = new JButton("Use Stairs");
		btnStairs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.buttonPressed(e);
			}
		});
		GridBagConstraints gbc_btnStairs = new GridBagConstraints();
		gbc_btnStairs.fill = GridBagConstraints.BOTH;
		gbc_btnStairs.insets = new Insets(0, 0, 5, 5);
		gbc_btnStairs.gridx = 0;
		gbc_btnStairs.gridy = 1;
		add(btnStairs, gbc_btnStairs);
		
		add(accuse, gbc_btnAccuse);
		
		endTurn = new JButton("End Turn");
		GridBagConstraints gbc_btnEndTurn = new GridBagConstraints();
		gbc_btnEndTurn.fill = GridBagConstraints.BOTH;
		gbc_btnEndTurn.insets = new Insets(0, 0, 5, 5);
		gbc_btnEndTurn.gridx = 0;
		gbc_btnEndTurn.gridy = 2;
		add(endTurn, gbc_btnEndTurn);
		
		endTurn.addActionListener(new ActionListener()
	    {
		      public void actionPerformed(ActionEvent e)
		      {
		    	  rollDice.setEnabled(true);
		    	  frame.buttonPressed(e);
		      }
		    });
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);
		
		
		textArea = new JTextArea(5,20);
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);

	}

	public JTextArea getTextArea() {
		return textArea;
	}
	
}
