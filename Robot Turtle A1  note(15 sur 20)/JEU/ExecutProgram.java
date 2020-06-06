package JEU;

import java.awt.BorderLayout;		
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import JEU.EnumCardColor;
import JEU.EnumWallType;
import JEU.EnumSpriteType;
import TUILE.Turtle;

public class ExecutProgram extends JFrame {		//not use
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton programButtun = new JButton("lancer le programme ");

	
	public ExecutProgram(Player player, EnumCardColor cardColor) {
		this.setSize(350, 150);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(1180, 300);

		JPanel buttonGroup1 = new JPanel();
		// On définit le layout en lui indiquant qu'il travaillera en ligne
		buttonGroup1.setLayout(new BoxLayout(buttonGroup1, BoxLayout.LINE_AXIS));
		buttonGroup1.add(programButtun); // affiche le bouton 1
		
		programButtun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			
			}
		});  
	}
}