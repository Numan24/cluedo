package cluedo.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

import cluedo.Game;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.DropMode;

public class OptionsPanel extends JPanel {
	
	private Game game;

	/**
	 * Create the panel.
	 */
	public OptionsPanel(Game game) {
		setBorder(new TitledBorder(null, "Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnRollDice = new JButton("Roll Dice");
		GridBagConstraints gbc_btnRollDice = new GridBagConstraints();
		gbc_btnRollDice.anchor = GridBagConstraints.WEST;
		gbc_btnRollDice.insets = new Insets(0, 0, 5, 5);
		gbc_btnRollDice.gridx = 0;
		gbc_btnRollDice.gridy = 3;
		add(btnRollDice, gbc_btnRollDice);
		
		JButton btnGuess = new JButton("Guess");
		GridBagConstraints gbc_btnGuess = new GridBagConstraints();
		gbc_btnGuess.insets = new Insets(0, 0, 5, 5);
		gbc_btnGuess.fill = GridBagConstraints.BOTH;
		gbc_btnGuess.gridx = 0;
		gbc_btnGuess.gridy = 4;
		add(btnGuess, gbc_btnGuess);
		
		JButton btnAccuse = new JButton("Accuse");
		GridBagConstraints gbc_btnAccuse = new GridBagConstraints();
		gbc_btnAccuse.insets = new Insets(0, 0, 5, 5);
		gbc_btnAccuse.fill = GridBagConstraints.BOTH;
		gbc_btnAccuse.gridx = 0;
		gbc_btnAccuse.gridy = 5;
		add(btnAccuse, gbc_btnAccuse);
		
		JButton btnEndTurn = new JButton("End Turn");
		GridBagConstraints gbc_btnEndTurn = new GridBagConstraints();
		gbc_btnEndTurn.insets = new Insets(0, 0, 5, 5);
		gbc_btnEndTurn.gridx = 0;
		gbc_btnEndTurn.gridy = 6;
		add(btnEndTurn, gbc_btnEndTurn);
		
		JTextArea textArea = new JTextArea(5,20);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 2;
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 7;
		add(textArea, gbc_textArea);

	}

}
