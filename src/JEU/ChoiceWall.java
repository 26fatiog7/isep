package JEU;


import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import JEU.Player;
import JEU.EnumWallType;
import TUILE.Wall;

public class ChoiceWall extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ChoiceWall(Board board, Player player,EnumWallType wallType) {
		this.setTitle("position mur "+(wallType==EnumWallType.Stone ? "Pierre" : "Glace"));
		this.setSize(350, 150);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(1180, 300); 
		
		JPanel container = new JPanel();
		this.setContentPane(container);
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		this.setVisible(true);
		
		JLabel XLabel = new JLabel("Colonne");
		JComboBox<String> XCombo = new JComboBox<String>();
		XCombo.setPreferredSize(new Dimension(100, 20));
		for (int i = 0; i < board.rowsCount; i++) {
			XCombo.addItem("" + i);
		}
		JPanel topLeftPanel = new JPanel();
		topLeftPanel.add(XLabel);
		topLeftPanel.add(XCombo);
		container.add(topLeftPanel, BorderLayout.WEST);
		
		JLabel YLabel = new JLabel("Ligne");
		JComboBox<String> YCombo = new JComboBox<String>();
		YCombo.setPreferredSize(new Dimension(100, 20));
		for (int i = 0; i < board.columnsCount; i++) {
			YCombo.addItem("" + i);
		}

		JPanel topRightPanel = new JPanel();
		topRightPanel.add(YLabel);
		topRightPanel.add(YCombo);
		container.add(topRightPanel, BorderLayout.EAST);

		JButton validateButton = new JButton("valider ma saisie");
		JPanel bottomPanel = new JPanel();
		container.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.add(validateButton);
		
		validateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int indexX = Integer.valueOf((String) XCombo.getSelectedItem());
				int indexY = Integer.valueOf((String) YCombo.getSelectedItem());
				validateButton.setEnabled(false);

				player.setWall(indexX, indexY, wallType);
				setVisible(false);
			}
		});
	}
}