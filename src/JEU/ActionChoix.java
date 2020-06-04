package JEU;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.View;

import TUILE.Wall;

public class ActionChoix extends JFrame {
	private JPanel pan = new JPanel();
	private JButton bouton3 = new JButton("placer mur glace");
	private JButton bouton4 = new JButton("placer mur pierre");
	private JButton bouton5 = new JButton("compléter le programme");
	private JButton bouton6 = new JButton("lancer le program");
	private JButton bouton7 = new JButton("se défausser");
	private JButton bouton8 = new JButton("fin du tour");

	public ActionChoix() {
		this.setTitle("Choisissez votre action");
		this.setSize(300, 200);
		this.setLocation(5, 300);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setLocationRelativeTo(null);
		this.setContentPane(new Panneau1());
		pan.add(bouton3);
		pan.add(bouton4); // creation bouton4
		pan.add(bouton5); // creation bouton5
		pan.add(bouton6); // creation bouton6
		pan.add(bouton7); // creation bouton7
		pan.add(bouton8); // creation bouton8

		JPanel b1 = new JPanel();
		// On définit le layout en lui indiquant qu'il travaillera en ligne
		b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
		b1.add(bouton3); // affiche le bouton 1
		b1.add(bouton4); // affiche le bouton 1
		this.getContentPane().add(b1);
		JPanel b2 = new JPanel();
		// Idem pour cette ligne
		b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS)); 
		b2.add(bouton5);
		// affiche le bouton 2
		this.getContentPane().add(b2);

		JPanel b3 = new JPanel();
		b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
		b3.add(bouton6);
		this.getContentPane().add(b3);

		JPanel b5 = new JPanel();
		b5.setLayout(new BoxLayout(b5, BoxLayout.LINE_AXIS));
		b5.add(bouton8);
		b5.add(bouton7);
		this.getContentPane().add(b5);

		// action des boutons
		bouton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				
			}
		});

		bouton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

			}
		});

		bouton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

			}
		});

		bouton6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

			}
		});

		bouton7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

			}
		});

		bouton8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

			}
		});

		this.setVisible(true);
	}
}