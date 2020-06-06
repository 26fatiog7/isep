package JEU;

import java.awt.Color;

import java.awt.Graphics;
import java.util.ArrayDeque;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.EnumMap;
import JEU.Board;
import JEU.BoardFrame;
import JEU.Card;
import TUILE.Turtle;
import TUILE.Wall;
import TUILE.Wood;
import JEU.EnumCardColor;

public class Player extends JPanel {
	public EnumSpriteType spriteType;
	private ArrayList<Card> cardsHeap;
	private ArrayList<Card> cardInHand;
	private ArrayList<Card> cardsHeap2;
	// private Card[] cardsInHand = new Card[5];
	private ArrayList<Card> program;
	private int StoneWallsCount = 3;
	private int IceWallsCount = 2;
	private int WoodCount = 2;
	private EnumMap<EnumWallType, Integer> remainingWallsCount = new EnumMap<EnumWallType, Integer>(EnumWallType.class);
	private Board board;
	protected int playerNum;
	private Turtle turtle;
	private static int currentPlayer = 0;
	// private Player player;
	private GameController gameCont;

	public Player(Board board, int playerNum, int indexX0) {
		System.out.println("creating playerNum " + playerNum);
		this.board = board;
		this.playerNum = playerNum;
		this.turtle = new Turtle(board, playerNum, indexX0);
		this.remainingWallsCount.put(EnumWallType.Stone, 3);
		this.remainingWallsCount.put(EnumWallType.Ice, 2);
		this.remainingWallsCount.put(EnumWallType.Wood, 1);
		this.program = new ArrayList<Card>();
		this.cardsHeap = new ArrayList<Card>();
		this.cardsHeap2 = new ArrayList<Card>();
		this.cardInHand = new ArrayList<Card>();

		// for (EnumCardColor cardColor : EnumCardColor.values()) {
		for (int i = 0; i < 18; i++) {
			this.cardsHeap.add(new Card(EnumCardColor.Blue));
		}
		for (int i = 0; i < 8; i++) {
			this.cardsHeap.add(new Card(EnumCardColor.Yellow));
			this.cardsHeap.add(new Card(EnumCardColor.Purple));
		}
		for (int i = 0; i < 3; i++) {
			this.cardsHeap.add(new Card(EnumCardColor.Laser));
		}
		System.out.println("pioche du joueur = " + cardsHeap);
		Collections.shuffle(cardsHeap);
		cardsHeap2 = cardsHeap;
		
		for (int i = 0; i < 5; i++) {
			this.cardInHand.add(cardsHeap.get(i));
			this.cardsHeap.remove(i);
		}
		System.out.println("main du joueur = " + cardInHand); // a lair de fonctionner mais affiche des trucs
		System.out.println("taille main du joueur = " + cardInHand.size()); // // // chelou
		System.out.println("programme taille = " + program.size());
	}

	public void moveToHeap(int index) {
		cardsHeap2.add(cardInHand.get(index));
	} 
	public void shuffleHeap() {
		Collections.shuffle(cardsHeap2);
	}
	public void changeheap() {
		while(cardsHeap2.size()>0) {
			cardsHeap.add(cardsHeap2.get(0));
			cardsHeap2.remove(0);
		}
	}
	
	public ArrayList<Card> getCardsHeap2() {
		return cardsHeap2;
	}

	public void setCardsHeap2(ArrayList<Card> cardsHeap2) {
		this.cardsHeap2 = cardsHeap2;
	}

	public void removeProgram(int index) {
		program.remove(0);
	}

	public void clearHand() {
		// for (int i = 0; i < cardInHand.size(); i++) {
		cardInHand.remove(1);
		cardInHand.remove(2);
		cardInHand.remove(3);
		cardInHand.remove(4);
		cardInHand.remove(5);
		System.out.println("taille de la main" + cardInHand.size());
		// }
		/*
		 * for (int i = 0; i < 5; i++) { cardInHand.add(cardsHeap.get(i));
		 * cardsHeap.remove(i); }
		 */
	}

	public void setCarteInProgram(ArrayList<Card> program) {
		this.program = program;
	}

	public ArrayList<Card> getCardInProgram() {
		return program;
	}

	public Card getCardProgram(int index) {
		return program.get(index);
	}

	public EnumCardColor getCardColorProgram(int index) {
		return program.get(index).color;
	}

	public void setCardInHand(ArrayList<Card> cardInHand) {
		this.cardInHand = cardInHand;
	}

	public ArrayList<Card> getCardsInHand() {
		return cardInHand;
	}

	public Card getCardHand(int indexCard) {
		return cardInHand.get(indexCard);
	}

	public ArrayList<Card> getCardsHeap() {
		return cardsHeap;
	}

	public void setCardsHeap(ArrayList<Card> cardsHeap) {
		this.cardsHeap = cardsHeap;
	}

	/*
	 * public void writeCardColor(EnumCardColor color) { //non utilisé switch
	 * (color) { case Blue: System.out.println("bleu"); break; case Yellow:
	 * System.out.println("jaune"); break; case Purple:
	 * System.out.println("violet"); break; case Laser: System.out.println("Laser");
	 * break; } }
	 */

	/*
	 * public void setWall(int indexX, int indexY, EnumWallType wallType) { if
	 * (remainingWallsCount.get(wallType) <= 0) { return; } new Wall(board, indexX,
	 * indexY, wallType); remainingWallsCount.put(wallType,
	 * remainingWallsCount.get(wallType) - 1); }
	 */

	// test
	public void setWall(int indexX, int indexY, EnumWallType wallType) {
		if (remainingWallsCount.get(wallType) <= 0) {
			return;
		} else { // tiles have to be cleared
			if (board.tiles[indexX][indexY] == null) { // case vide => draw
				new Wall(board, indexX, indexY, wallType);
				remainingWallsCount.put(wallType, remainingWallsCount.get(wallType) - 1);
			} else { // try
						// again
				return;
			}
		}
	}// fin test
	
	public void setWood(int indexX, int indexY, EnumWallType Wood) {
		if (remainingWallsCount.get(Wood) <= 0) {
			return;
		} else { // tiles have to be cleared
			if (board.tiles[indexX][indexY] == null) { // case vide => draw
				new Wood(board, indexX, indexY);
				remainingWallsCount.put(Wood, remainingWallsCount.get(Wood) - 1);
			} else { // try
						// again
				return;
			}
		}
	}// fin test
	
	public void DestroyWood(EnumWallType Wood) {
		remainingWallsCount.put(Wood, remainingWallsCount.get(Wood)+1);
	}
	

	public void completHand() {
		while (cardInHand.size() < 5) {
			cardInHand.add(cardsHeap.get(0));
			cardsHeap.remove(0);
		}
	}

	public void addCardToProgram(int cardInHandNum) {
		if (cardInHand.get(cardInHandNum) == null) {
			return;
		}
		program.add(cardInHand.get(cardInHandNum));
		// cardInHand.remove(cardInHandNum);
	}

	public void removeCardToHand(int cardInHandNum) {
		if (cardInHand.get(cardInHandNum) == null) {
			return;
		}
		cardInHand.remove(cardInHandNum);
	}

	

	public void affichageNbCarterogram(Graphics g) {
		g.drawString("ton programme comporte : " + program.size(), 5, 50);
	}

	public Turtle getTurtle() {
		return turtle;
	}

	public void setTurtle(Turtle turtle) {
		this.turtle = turtle;
	}
}
/*
 * public void clearHand(Player player) { // a tester for (int i = 0; i <=
 * cardsInHand.length; i++) { cardsInHand[i] = null; } for (int i = 0; i < 5;
 * i++) { cardsInHand[i] = this.cardsHeap.getFirst(); cardsHeap.removeFirst();
 * System.out.println("cards in hands = " + cardsInHand.toString()); } }
 */
