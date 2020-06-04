package JEU;

import java.awt.Dimension;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import JEU.BoardFrame;
import TUILE.Wall;
import TUILE.Wood;
import JEU.Board;
import JEU.Player;
import TUILE.Gem;
import TUILE.Turtle;
import TUILE.Tile;
import JEU.EnumNbPlayer;

import javax.swing.JButton;

public class GameController extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Player player;
	private Board board;
	public static ArrayList<Player> players;
	private ArrayList<Tile> gems;
	private EnumNbPlayer nbPlayer;
	
	public int nbPlayers;
	private int indexX;
	
	private JButton button2Players = new JButton("2 joueurs");
	private JButton button3Players = new JButton("3 joueurs");
	private JButton button4Players = new JButton("4 joueurs");
	
	private BoardFrame boardFrame;
	
	Dimension size = getPreferredSize();
 
	public GameController() {
		this.setTitle("Bienvenue dans ROBOT TURTLE");
		this.setSize(630, 1500);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(new Panneau1());
 
		JPanel buttonGroup1 = new JPanel();
		// On définit le layout en lui indiquant qu'il travaillera en ligne
		buttonGroup1.setLayout(new BoxLayout(buttonGroup1, BoxLayout.LINE_AXIS));
		buttonGroup1.add(button2Players); // affiche le bouton 1

		JPanel buttonGroup2 = new JPanel();
		// Idem pour cette ligne
		buttonGroup2.setLayout(new BoxLayout(buttonGroup2, BoxLayout.LINE_AXIS));
		buttonGroup2.add(button3Players);
		buttonGroup2.add(button4Players); // affiche le bouton 2 et 3 sur meme ligne

		JPanel buttonsLayout = new JPanel();
		// On positionne maintenant ces deux lignes en colonne
		buttonsLayout.setLayout(new BoxLayout(buttonsLayout, BoxLayout.PAGE_AXIS));
		buttonsLayout.add(buttonGroup1);
		buttonsLayout.add(buttonGroup2); // button4JPanel contient button2JPanel et button1JPanel

		this.getContentPane().add(buttonsLayout);

		button2Players.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				initializeNewGame(2, EnumNbPlayer.Deux);
				new Gem(board, 3, 7);   
				players = new ArrayList<Player>(); 
				players.add( new Player(board, 1,1));
				players.add( new Player(board, 2,5));
				
				for (int i = 0; i <= 7; i++) {
					new Wall(board, 7,i,EnumWallType.Stone);
				}
				launchGame();
				System.out.println("ca marche");
				System.out.println("nb joueurs = "+ players.size());
			}
		});
 
		button3Players.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				initializeNewGame(3,EnumNbPlayer.TroisQuatre);
				new Gem(board, 0, 7);
				new Gem(board, 3, 7);
				new Gem(board, 6, 7);
				//new Wood(board, 5, 5);
			 	players = new ArrayList<Player>();
				players.add( new Player(board, 1,0));
				players.add( new Player(board, 2,3));
				players.add( new Player(board, 3,6));
		 		
				for (int i = 0; i <= 7; i++) {
					new Wall(board, 7,i,EnumWallType.Stone);
				}
				launchGame();
				System.out.println("ca marche");
				System.out.println("nb joueurs = "+ players.size());
				
			}
		});

		button4Players.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				initializeNewGame(4, EnumNbPlayer.TroisQuatre);
				new Gem(board, 1, 7);
				new Gem(board, 6, 7);
				//new Gem(board, 5, 7);
				//new Gem(board, 7, 7);
				players = new ArrayList<Player>();
				players.add( new Player(board, 1,0));
				players.add( new Player(board, 2,2));
				players.add( new Player(board, 3,5));
				players.add( new Player(board, 4,7));
				launchGame();
				System.out.println("ca marche");
				System.out.println("nb joueurs = "+ players.size());
			}
		});

		this.setVisible(true);
	}

	public void initializeNewGame(int playerCount, EnumNbPlayer nbPlayer) {
		this.board = new Board();
		this.boardFrame = new BoardFrame(board);
	}

		
	public void launchGame() {
		new NextStepSelection(this.board, players,1, player);
		
		/*JOptionPane jop1;
		jop1 = new JOptionPane();
		jop1.showMessageDialog(null, "A partir de maintenant ferme les petites fenêtres après les avoir utilisées à chaque tour et pareil pour plus tard !!!!!!", "soyez vigilents", JOptionPane.WARNING_MESSAGE);
		*/
		//new PlayerPanel();
	}
	/*	players = new ArrayList<Player>();
		for(int i =1; i<=playerCount; i++) {
			players.add( new Player(board, i));
		}*/ 
}