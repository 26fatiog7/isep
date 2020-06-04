package JEU;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import JEU.EnumCardColor;
import JEU.CardsPanel;
import JEU.ButtonEnable;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PlayerPanel2 extends JFrame {
	// private JPanel pan = new JPanel();
	private Player player;
	private GameController gameCont;
	private EnumCardColor cardColor;
	
	private JButton[] labelF = new JButton[6];
	private CardsPanel cardsPanel;
	private ButtonEnable boutons;
	private boolean[] boutonOff = new boolean[5];
	private boolean[] boutonOffF = new boolean[5];
	private boolean[] cardToHeap = new boolean[5];

	// private JPanel container;

	public PlayerPanel2(ArrayList<Player> players, Player player, int currentPlayer, EnumCardColor cardColor) {
		this.setTitle("ajout carte program joueur " + players.get(currentPlayer).playerNum);
		this.setSize(350, 300);
		this.setLocation(5, 5);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setLocationRelativeTo(null);
		// this.setContentPane(new CardsPanel());
		// this.setVisible(true);

		

		labelF[0] = new JButton("" + player.getCardHand(0).color);
		labelF[1] = new JButton("" + player.getCardHand(1).color);
		labelF[2] = new JButton("" + player.getCardHand(2).color);
		labelF[3] = new JButton("" + player.getCardHand(3).color);
		labelF[4] = new JButton("" + player.getCardHand(4).color);
		labelF[5] = new JButton("défausser");

		JPanel container = new JPanel();
		this.setContentPane(container);
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		this.setVisible(true);

		
		
		JPanel buttonGroup1F = new JPanel();
		// On définit le layout en lui indiquant qu'il travaillera en ligne
		buttonGroup1F.setLayout(new BoxLayout(buttonGroup1F, BoxLayout.LINE_AXIS));
		buttonGroup1F.add(labelF[0]);
		buttonGroup1F.add(labelF[1]);
		buttonGroup1F.add(labelF[2]);

		JPanel buttonGroup2F = new JPanel();
		// Idem pour cette ligne
		buttonGroup2F.add(labelF[3]);
		buttonGroup2F.add(labelF[4]);
		buttonGroup2F.add(labelF[5]);

		JPanel buttonsLayoutF = new JPanel();
		// On positionne maintenant ces deux lignes en colonne
		buttonsLayoutF.setLayout(new BoxLayout(buttonsLayoutF, BoxLayout.PAGE_AXIS));
		buttonsLayoutF.add(buttonGroup1F);
		buttonsLayoutF.add(buttonGroup2F);
		container.add(buttonsLayoutF, BorderLayout.CENTER);

		for (int i = 0; i < player.getCardsInHand().size(); i++) {
			System.out.println("tu as dans ta main : " + player.getCardHand(i).color);
		}

		labelF[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				labelF[0].setEnabled(false);
				boutonOffF[0] = true;
				cardToHeap[0] = true;
			}
		});
		labelF[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				labelF[1].setEnabled(false);
				boutonOffF[1] = true;
				cardToHeap[1] = true;
			}
		});
		labelF[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				labelF[2].setEnabled(false);
				boutonOffF[2] = true;
				cardToHeap[2] = true;
			}
		});
		labelF[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				labelF[3].setEnabled(false);
				boutonOffF[3] = true;
				cardToHeap[3] = true;
			}
		});
		labelF[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				labelF[4].setEnabled(false);
				boutonOffF[4] = true;
				cardToHeap[4] = true;
			}
		});
		labelF[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				labelF[0].setEnabled(false); 
				labelF[1].setEnabled(false);
				labelF[2].setEnabled(false);
				labelF[3].setEnabled(false);
				labelF[4].setEnabled(false);
				labelF[5].setEnabled(false);
				
				if (cardToHeap[0]==true) {
					player.moveToHeap(0);
				}
				if (cardToHeap[1]==true) {
					player.moveToHeap(1);
				}
				if (cardToHeap[2]==true) {
					player.moveToHeap(2);
				}
				if (cardToHeap[3]==true) {
					player.moveToHeap(3);
				}
				if (cardToHeap[4]==true) {
					player.moveToHeap(4);
				}
				
				if (boutonOffF[4] == true) {
					
					player.removeCardToHand(4);
				}
				if (boutonOffF[3] == true) {
					player.removeCardToHand(3);
				}
				if (boutonOffF[2] == true) {
					player.removeCardToHand(2);
				}
				if (boutonOffF[1] == true) {
					player.removeCardToHand(1);
				}
				if (boutonOffF[0] == true) {
					player.removeCardToHand(0);
				}
				
				
			
				/*if(player.getCardsHeap().size()<4) {
					player.changeHeap();
				}*/
				/*
				 * for (int i = 0; i < player.getCardInProgram().size(); i++) {
				 * System.out.println("ton programme est a letape 3F: " +
				 * player.getCardProgram(i).color); }
				 */
				for (int i = 0; i < player.getCardsInHand().size(); i++) {
					System.out.println("tu as dans ta main : " + player.getCardHand(i).color);
				}
				player.completHand();
				System.out.println("nb carte in pioche = " + player.getCardsHeap().size());
				
				setVisible(false);
			}
		});
		this.setVisible(true);
	}
}