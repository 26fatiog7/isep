package JEU;

import JEU.ChoiceWall;


import JEU.Player;
import TUILE.Turtle;
import JEU.EnumWallType;
import JEU.EnumCardColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class NextStepSelection extends JFrame {
	private JPanel buttonsPanel = new JPanel();
	private JButton newStoneButton = new JButton("placer mur pierre");
	private JButton newWoodButton = new JButton("placer une boîte en bois");
	private JButton newIceButton = new JButton("placer mur glace");
	private JButton addToProgram = new JButton("compléter le programme");
	private JButton launchProgram = new JButton("lancer le programme");
	private JButton clearHand = new JButton("se défausser");
	private JButton nextPlayer = new JButton("finir mon tour");
	private Board board;
	private Player player;
	private EnumCardColor cardColor;
	private EnumNbPlayer nbPlayer;
	private EnumSpriteType spriteType;
	private Turtle turtle;
	private PlayerPanel playerPanel;

	public NextStepSelection(Board board, ArrayList<Player> players, int currentPlayer, Player player) {
		System.out.println("next Step Selection for player " + currentPlayer);
		this.setTitle("Action pour le Joueur " + players.get(currentPlayer).playerNum);
		this.setSize(370, 200);
		this.setLocation(0, 300);
		this.setContentPane(new Panneau1());
		buttonsPanel.add(newStoneButton);
		buttonsPanel.add(newIceButton); // creation bouton4
		buttonsPanel.add(newWoodButton);
		buttonsPanel.add(addToProgram); // creation bouton5
		buttonsPanel.add(launchProgram); // creation bouton6
		buttonsPanel.add(clearHand); // creation bouton7
		buttonsPanel.add(nextPlayer); // creation bouton8

		JPanel topButtonRow = new JPanel();
		// On définit le layout en lui indiquant qu'il travaillera en ligne
		topButtonRow.setLayout(new BoxLayout(topButtonRow, BoxLayout.LINE_AXIS));
		topButtonRow.add(newStoneButton); // affiche le bouton 1
		topButtonRow.add(newIceButton);
		this.getContentPane().add(topButtonRow);
		
		
		JPanel top2ButtonRow = new JPanel();
		// On définit le layout en lui indiquant qu'il travaillera en ligne
		top2ButtonRow.setLayout(new BoxLayout(top2ButtonRow, BoxLayout.LINE_AXIS));
		top2ButtonRow.add(newWoodButton);// affiche le bouton 1
		this.getContentPane().add(top2ButtonRow);
		
		
		JPanel secondButtonRow = new JPanel();
		// Idem pour cette ligne
		secondButtonRow.setLayout(new BoxLayout(secondButtonRow, BoxLayout.LINE_AXIS));
		secondButtonRow.add(addToProgram);
		this.getContentPane().add(secondButtonRow);

		JPanel thirdButtonRow = new JPanel();
		thirdButtonRow.setLayout(new BoxLayout(thirdButtonRow, BoxLayout.LINE_AXIS));
		thirdButtonRow.add(launchProgram);
		this.getContentPane().add(thirdButtonRow);

		JPanel botomButtonRow = new JPanel();
		botomButtonRow.setLayout(new BoxLayout(botomButtonRow, BoxLayout.LINE_AXIS));
		botomButtonRow.add(nextPlayer);
		botomButtonRow.add(clearHand);
		this.getContentPane().add(botomButtonRow);
		

		newStoneButton.addActionListener(new ActionListener() { // ok
			public void actionPerformed(ActionEvent event) {
				new ChoiceWall(board, players.get(currentPlayer), EnumWallType.Stone);
				addToProgram.setEnabled(false);
				newStoneButton.setEnabled(false);
				newIceButton.setEnabled(false);
				launchProgram.setEnabled(false);
				newWoodButton.setEnabled(false);
			}
		});
 
		newIceButton.addActionListener(new ActionListener() { // ok
			public void actionPerformed(ActionEvent event) {
				new ChoiceWall(board, players.get(currentPlayer), EnumWallType.Ice);
				addToProgram.setEnabled(false);
				newStoneButton.setEnabled(false);
				newIceButton.setEnabled(false);
				launchProgram.setEnabled(false);
				newWoodButton.setEnabled(false);
			}
		});
		
		
/*		newWoodButton.addActionListener(new ActionListener() { // ok
			public void actionPerformed(ActionEvent event) {
				new ChoiceWall2(board, players.get(currentPlayer), EnumWallType.Wood);
				addToProgram.setEnabled(false);
				newStoneButton.setEnabled(false);
				newWoodButton.setEnabled(false);
				newIceButton.setEnabled(false);
				launchProgram.setEnabled(false);
			}
		});*/

		addToProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				new PlayerPanel(players, players.get(currentPlayer), currentPlayer, cardColor);
				addToProgram.setEnabled(false);
				newStoneButton.setEnabled(false);
				newIceButton.setEnabled(false);
				launchProgram.setEnabled(false);
				clearHand.setEnabled(false);

			/*	JOptionPane jop1;
				jop1 = new JOptionPane();
				jop1.showMessageDialog(null,
						"la rangée du haut sert à ajoutter des cartes à ton programme /// la rangée du bas sert à te défausser /// même si tu ne te défausse pas appuie quand même sur -Défausser-",
						"attention!!!", JOptionPane.WARNING_MESSAGE);*/
			}
		});

		launchProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				new CardsPanel(players, players.get(currentPlayer), currentPlayer, cardColor);
				//System.out.print("ton program est : " + player.getCardsInHand());
			/*	for(int i=0; i < player.getCardInProgram().size(); i++) {	
				turtle.Move(player.getCardProgram(i).color, spriteType);
					player.removeProgram(i);
				} */
 				addToProgram.setEnabled(false);
				newStoneButton.setEnabled(false);
				newIceButton.setEnabled(false);
				launchProgram.setEnabled(false);
				newWoodButton.setEnabled(false);
			}
		});

		clearHand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				new PlayerPanel2(players, players.get(currentPlayer), currentPlayer, cardColor);
				addToProgram.setEnabled(true);
				newStoneButton.setEnabled(true);
				newWoodButton.setEnabled(true);
				newIceButton.setEnabled(true);
				launchProgram.setEnabled(true);// ebale et enable
				Collections.rotate(players, -1);
				setVisible(false);
				new NextStepSelection(board, players, currentPlayer, player);
			}
		});

		nextPlayer.addActionListener(new ActionListener() { // a tester
			public void actionPerformed(ActionEvent event) {
				Collections.rotate(players, -1);
				addToProgram.setEnabled(true);
				newStoneButton.setEnabled(true);
				newIceButton.setEnabled(true);
				launchProgram.setEnabled(true);
				clearHand.setEnabled(true);
				newWoodButton.setEnabled(true);
				setVisible(false);
				new NextStepSelection(board, players, currentPlayer, player);
				
				JOptionPane jop2;
				/*jop2 = new JOptionPane();
				jop2.showMessageDialog(null,
						"Joueur Suivant",
						"Joueur suivant", JOptionPane.WARNING_MESSAGE);*/
			}
		});
		this.setVisible(true);
		
	}
}