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

public class PlayerPanel extends JFrame {
	// private JPanel pan = new JPanel();
	private Player player;
	private GameController gameCont;
	private EnumCardColor cardColor;
	private JButton[] label = new JButton[5];
	private JButton[] labelF = new JButton[6];
	private CardsPanel cardsPanel;
	private ButtonEnable boutons;
	private boolean[] boutonOff = new boolean[5];
	private boolean[] boutonOffF = new boolean[5];
	private boolean[] cardToHeap = new boolean[5];

	// private JPanel container;

	public PlayerPanel(ArrayList<Player> players, Player player, int currentPlayer, EnumCardColor cardColor) {
		this.setTitle("ajout carte program joueur " + players.get(currentPlayer).playerNum);
		this.setSize(350, 300);
		this.setLocation(5, 5);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setLocationRelativeTo(null);
		// this.setContentPane(new CardsPanel());
		// this.setVisible(true);

		for (int i = 0; i < 5; i++) {
			System.out.println("card in hand " + player.getCardHand(i).color);
		}
		label[0] = new JButton("" + player.getCardHand(0).color);
		label[1] = new JButton("" + player.getCardHand(1).color);
		label[2] = new JButton("" + player.getCardHand(2).color);
		label[3] = new JButton("" + player.getCardHand(3).color);
		label[4] = new JButton("" + player.getCardHand(4).color);

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

		JPanel buttonGroup1 = new JPanel();
		// On définit le layout en lui indiquant qu'il travaillera en ligne
		buttonGroup1.setLayout(new BoxLayout(buttonGroup1, BoxLayout.LINE_AXIS));
		buttonGroup1.add(label[0]);
		buttonGroup1.add(label[1]);
		buttonGroup1.add(label[2]);

		JPanel buttonGroup2 = new JPanel();
		// Idem pour cette ligne
		buttonGroup2.setLayout(new BoxLayout(buttonGroup2, BoxLayout.LINE_AXIS));
		buttonGroup2.add(label[3]);
		buttonGroup2.add(label[4]);

		JPanel buttonsLayout = new JPanel();
		// On positionne maintenant ces deux lignes en colonne
		buttonsLayout.setLayout(new BoxLayout(buttonsLayout, BoxLayout.PAGE_AXIS));
		buttonsLayout.add(buttonGroup1);
		buttonsLayout.add(buttonGroup2);
		container.add(buttonsLayout, BorderLayout.NORTH);

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
		container.add(buttonsLayoutF, BorderLayout.SOUTH);

		this.getContentPane().add(buttonsLayout);

		label[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				player.addCardToProgram(0);
				label[0].setEnabled(false);
				labelF[0].setEnabled(false);
				boutonOffF[0] = true;
				/*
				 * for (int i = 0; i < player.getCardInProgram().size(); i++) {
				 * System.out.println("ton programme est : " + player.getCardProgram(i).color);
				 * }
				 */
				System.out.print("ton program est : " + player.getCardInProgram());
				for (int i = 0; i < player.getCardsInHand().size(); i++) {
					System.out.println("tu as dans ta main : " + player.getCardHand(i).color);
				}
			}
		});
		label[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				player.addCardToProgram(1);
				label[1].setEnabled(false);
				labelF[1].setEnabled(false);
				boutonOffF[1] = true;
				// for (int i = 0; i < player.getCardInProgram().size(); i++) {
				// System.out.println("ton programme est a l'étape1: " +
				// player.getCardProgram(i).color);
				// }
				System.out.print("ton program est : " + player.getCardInProgram());
				for (int i = 0; i < player.getCardsInHand().size(); i++) {
					System.out.println("tu as dans ta main : " + player.getCardHand(i).color);
				}
			}
		});
		label[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				player.addCardToProgram(2);
				label[2].setEnabled(false);
				labelF[2].setEnabled(false);
				boutonOffF[2] = true;
				for (int i = 0; i < player.getCardInProgram().size(); i++) {
					System.out.println("ton programme est  a l'étape2: " + player.getCardProgram(i).color);
				}
				for (int i = 0; i < player.getCardsInHand().size(); i++) {
					System.out.println("tu as dans ta main : " + player.getCardHand(i).color);
				}
			}
		});
		label[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				player.addCardToProgram(3);
				label[3].setEnabled(false);
				labelF[3].setEnabled(false);
				boutonOffF[3] = true;
				/*
				 * for (int i = 0; i < player.getCardInProgram().size(); i++) {
				 * System.out.println("ton programme est a l'étape3 : " +
				 * player.getCardProgram(i).color); }
				 */
				System.out.print("ton program est : " + player.getCardInProgram());
				for (int i = 0; i < player.getCardsInHand().size(); i++) {
					System.out.println("tu as dans ta main : " + player.getCardHand(i).color);
				}
			}
		});
		label[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				player.addCardToProgram(4);
				label[4].setEnabled(false);
				labelF[4].setEnabled(false);
				boutonOffF[4] = true;
				for (int i = 0; i < player.getCardInProgram().size(); i++) {
					System.out.println("ton programme est  a l'étape4: " + player.getCardProgram(i).color);
				}
				for (int i = 0; i < player.getCardsInHand().size(); i++) {
					System.out.println("tu as dans ta main : " + player.getCardHand(i).color);
				}
			}
		});

		labelF[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				labelF[0].setEnabled(false);
				label[0].setEnabled(false);
				boutonOffF[0] = true;
				cardToHeap[0] = true;
				for (int i = 0; i < player.getCardInProgram().size(); i++) {
					System.out.println("ton programme est a letape 0F: " + player.getCardProgram(i).color);
				}
				for (int i = 0; i < player.getCardsInHand().size(); i++) {
					System.out.println("tu as dans ta main : " + player.getCardHand(i).color);
				}
			}
		});
		labelF[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				labelF[1].setEnabled(false);
				label[1].setEnabled(false);
				boutonOffF[1] = true;
				cardToHeap[1] = true;
				for (int i = 0; i < player.getCardInProgram().size(); i++) {
					System.out.println("ton programme est a letape 1F: " + player.getCardProgram(i).color);
				}
				for (int i = 0; i < player.getCardsInHand().size(); i++) {
					System.out.println("tu as dans ta main : " + player.getCardHand(i).color);
				}
			}
		});
		labelF[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				labelF[2].setEnabled(false);
				label[2].setEnabled(false);
				boutonOffF[2] = true;
				cardToHeap[2] = true;
				for (int i = 0; i < player.getCardInProgram().size(); i++) {
					System.out.println("ton programme est a letape 2F: " + player.getCardProgram(i).color);
				}
				for (int i = 0; i < player.getCardsInHand().size(); i++) {
					System.out.println("tu as dans ta main : " + player.getCardHand(i).color);
				}
			}
		});
		labelF[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				labelF[3].setEnabled(false);
				label[3].setEnabled(false);
				boutonOffF[3] = true;
				cardToHeap[3] = true;
				for (int i = 0; i < player.getCardInProgram().size(); i++) {
					System.out.println("ton programme est a letape 3F: " + player.getCardProgram(i).color);
				}
				for (int i = 0; i < player.getCardsInHand().size(); i++) {
					System.out.println("tu as dans ta main : " + player.getCardHand(i).color);
				}
			}
		});
		labelF[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				labelF[4].setEnabled(false);
				label[4].setEnabled(false);
				boutonOffF[4] = true;
				cardToHeap[4] = true;
				for (int i = 0; i < player.getCardInProgram().size(); i++) {
					System.out.println("ton programme est a l'etape 4F : " + player.getCardProgram(i).color);
				}
				for (int i = 0; i < player.getCardsInHand().size(); i++) {
					System.out.println("tu as dans ta main : " + player.getCardHand(i).color);
				}
			}
		});
		labelF[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				label[0].setEnabled(false);
				label[1].setEnabled(false);
				label[2].setEnabled(false);
				label[3].setEnabled(false);
				label[4].setEnabled(false);
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
				
				if (player.getCardsHeap().size()<1) {
					player.shuffleHeap();
					player.changeheap();
				}
				
				/*
				 * for (int i = 0; i < player.getCardInProgram().size(); i++) {
				 * System.out.println("ton programme est a letape 3F: " +
				 * player.getCardProgram(i).color); }
				 */
				for (int i = 0; i < player.getCardInProgram().size(); i++) {
					System.out.println("ton program est : " + player.getCardProgram(i).color);
				}
				for (int i = 0; i < player.getCardsInHand().size(); i++) {
					System.out.println("tu as dans ta main : " + player.getCardHand(i).color);
				}
				System.out.println("nb carte in programm = " + player.getCardInProgram().size());
				player.completHand();
				System.out.println("taille programme = " + player.getCardsHeap().size());
				System.out.println("taille defausse = " + player.getCardsHeap2().size());
				setVisible(false);
			} 
		});
		this.setVisible(true);
	}
}