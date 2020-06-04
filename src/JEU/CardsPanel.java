package JEU;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import JEU.EnumCardColor;
import TUILE.Turtle;
import JEU.EnumSpriteType;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CardsPanel extends JFrame {
	// private JPanel pan = new JPanel();
	private Player player;
	private GameController gameCont;
	//private EnumCardColor cardColor;
	private JPanel buttonsPanel = new JPanel();
	private JButton launchProgramme = new JButton("lancer le programme");
	private Turtle turtle;
	private EnumSpriteType spriteType;

	public CardsPanel(ArrayList<Player> players, Player playerBis, int currentPlayer, EnumCardColor cardColor) {
		this.setTitle("Execution du programme");
		this.setSize(150, 150);
		this.setLocation(5, 500);
 
		buttonsPanel.add(launchProgramme);
		players = GameController.players;
		Player player = players.get(currentPlayer);

		JPanel container = new JPanel();
		this.setContentPane(container);
		container.setBackground(Color.red);
		container.setLayout(new BorderLayout());
		this.setVisible(true);

		container.add(launchProgramme, BorderLayout.CENTER);
		for (int i = 0; i < player.getCardInProgram().size(); i++) {
			System.out.println("ton program est : " + player.getCardProgram(i).color);
		}
		launchProgramme.addActionListener(new ActionListener() { // ok
			public void actionPerformed(ActionEvent event) {
				
				while( player.getCardInProgram().size() != 0) {
					player.getTurtle().move(player.getCardColorProgram(0));
					player.removeProgram(0);
					setVisible(false);
				}
				/*
				 * for(int i=0; i < player.getCardInProgram().size(); i++) {
				 * turtle.Move(player.getCardProgram(i).color, spriteType);
				 * player.removeProgram(i); }
				 */
			}
		});
		this.setVisible(true);
	}
}