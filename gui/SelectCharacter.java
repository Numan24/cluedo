package cluedo.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JRadioButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;

public class SelectCharacter extends JPanel {

	/**
	 * Create the panel.
	 */
	public SelectCharacter() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Miss Scarlett");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Colonel Mustard");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JRadioButton missScarlett = new JRadioButton("");
		GridBagConstraints gbc_missScarlett = new GridBagConstraints();
		gbc_missScarlett.insets = new Insets(0, 0, 5, 5);
		gbc_missScarlett.gridx = 1;
		gbc_missScarlett.gridy = 2;
		add(missScarlett, gbc_missScarlett);
		
		JRadioButton colonelMustard = new JRadioButton("");
		GridBagConstraints gbc_colonelMustard = new GridBagConstraints();
		gbc_colonelMustard.insets = new Insets(0, 0, 5, 5);
		gbc_colonelMustard.gridx = 2;
		gbc_colonelMustard.gridy = 2;
		add(colonelMustard, gbc_colonelMustard);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("New radio button");
		GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_2.gridx = 3;
		gbc_rdbtnNewRadioButton_2.gridy = 2;
		add(rdbtnNewRadioButton_2, gbc_rdbtnNewRadioButton_2);

	}

}
