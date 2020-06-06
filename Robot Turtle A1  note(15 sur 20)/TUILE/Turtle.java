package TUILE;

import java.awt.Graphics;

import java.awt.Image;
import java.rmi.Remote;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import JEU.Board;
import JEU.Card;
import JEU.EnumCardColor;
import JEU.EnumSpriteType;
import JEU.Player;
import JEU.EnumNbPlayer;

public class Turtle extends Sprite {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int playerNum;
	// protected int indexX;
	private boolean targetReached;
	protected int deltaIndexX;
	protected int deltaIndexY;
	protected int deltaIndexZ;
	protected int indexX0;
	private Player player;
	private EnumNbPlayer nbPlayer;
	public static ArrayList<Player> players;
	private Wall wall;
	private int valX;
	private int valY;
	// private Board board;

	public Turtle(Board board, int playerNum, int indexX) {
		super(board, playerNum, indexX);
		this.spriteType = EnumSpriteType.Turtle;
		this.playerNum = playerNum;
		this.indexX0 = indexX;
		this.reset();
		this.imgSprite = (new ImageIcon(getClass().getResource("/image/turtle" + playerNum + ".png"))).getImage();
		this.targetReached = false;
	}

	public void reset() {
		this.deltaIndexX = 0; // vectors move
		this.deltaIndexY = 1;
		this.setPosition(this.indexX0, 0);
	}

	public void move(EnumCardColor cardColor) {
		System.out.println("Move trouvé");
		System.out.println("delatIndexX= " + deltaIndexX);
		System.out.println("delatIndexY= " + deltaIndexY);
		int newIndexX;
		int newIndexY;
		// change direction:
		switch (cardColor) {
		case Yellow: // turn left
			this.deltaIndexZ = this.deltaIndexY;
			this.deltaIndexY = -this.deltaIndexX;
			this.deltaIndexX = this.deltaIndexZ;

			System.out.println("delatIndexX= " + deltaIndexX);
			System.out.println("delatIndexY= " + deltaIndexY);

			break;
		case Purple: // turn right
			this.deltaIndexZ = this.deltaIndexX;
			this.deltaIndexX = -this.deltaIndexY;
			this.deltaIndexY = this.deltaIndexZ;
			System.out.println("delatIndexX= " + deltaIndexX);
			System.out.println("delatIndexY= " + deltaIndexY);
			break;
		case Blue: // move 1 tile
			System.out.println("bleu trouvé");
			System.out.println("delatIndexX= " + deltaIndexX);
			System.out.println("delatIndexY= " + deltaIndexY);

			// System.out.println("newindexX = " + newIndexX);
			newIndexX = indexX + this.deltaIndexX;
			newIndexY = indexY + this.deltaIndexY;

			// if out of board, no move:
			System.out.println("newindexX = " + newIndexX);
			System.out.println("newindexY = " + newIndexY);

			if (newIndexX == -1 || newIndexY == -1 || newIndexX == 8 || newIndexY == 8) { // out of board
				this.reset();
			}

			/*
			 * if (newIndexX < 0) { ((Turtle) board.tiles[newIndexX][newIndexY]).reset();
			 * this.reset(); } if (newIndexY < 0) { ((Turtle)
			 * board.tiles[newIndexX][newIndexY]).reset(); this.reset(); } if (newIndexX >
			 * 7) { ((Turtle) board.tiles[newIndexX][newIndexY]).reset(); this.reset(); } if
			 * (newIndexY > 7) { ((Turtle) board.tiles[newIndexX][newIndexY]).reset();
			 * this.reset(); }
			 */

			// if touch other turtle:
			if (newIndexX < 8 && newIndexX >= 0 && newIndexY < 8 && newIndexY >= 0) {
				if (board.tiles[newIndexX][newIndexY] == null) {
					setPosition(newIndexX, newIndexY);
				} else {
					switch (board.tiles[newIndexX][newIndexY].spriteType) {
					case Turtle:
						((Turtle) board.tiles[newIndexX][newIndexY]).reset();
						this.reset();
						break;
					case StoneWall:
					case IceWall:
						this.deltaIndexX = -this.deltaIndexX;
						this.deltaIndexY = -this.deltaIndexY;
						break;
					case Gem:		//to finish
						JOptionPane jop1;
						jop1 = new JOptionPane();
						jop1.showMessageDialog(null, "bravo  tu as fini la partie 1er +3 point  // 2eme +2pts //3eme +1 point", "NOUS AVONS UN VAINQUEUR!!!",
								JOptionPane.WARNING_MESSAGE);						
						targetReached = true;
						board.sprites.remove(board.tiles[indexX][indexY]); // ice[x][y] no more in sprite list
						//board.tiles[indexX + deltaIndexX][indexY + deltaIndexY] = null; // case null
						board.paintComponent(board.getGraphics()); // force l'appel à la methode de redisseneer
						setPosition(newIndexX, newIndexY);
						
						break;
					/*case Wood:
						board.sprites.remove(board.tiles[indexX + deltaIndexX][indexY + deltaIndexY]);
						board.tiles[indexX + deltaIndexX][indexY + deltaIndexY] = null; // case null
						newIndexX = indexX + this.deltaIndexX;
						newIndexY = indexY + this.deltaIndexY;
						*/
					}
				}
			}
			board.paintComponent(board.getGraphics()); // tile to tile
			try {
				Thread.sleep(400); // time laps
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case Laser:								//detection correctly fonctionnel after 5 days !!!!!!!
			System.out.println("delatIndexX= " + deltaIndexX);
			System.out.println("delatIndexY= " + deltaIndexY);
			int valX = deltaIndexX; // load vector
			int valY = deltaIndexY;
			
			if (board.tiles[indexX + deltaIndexX][indexY + deltaIndexY] == null
					&& (this.deltaIndexX + this.deltaIndexY) > 0 &&  indexX + deltaIndexX < 8 && indexY + deltaIndexY < 8) { // x et y > 0
				while (board.tiles[indexX + deltaIndexX][indexY + deltaIndexY] == null
				 		&& deltaIndexX + deltaIndexY < 7 - (indexX * valX + indexY * valY)) {
					this.deltaIndexX = (deltaIndexX + 1) * valX;
					this.deltaIndexY = (deltaIndexY + 1) * valY;
					System.out.println("deltaX =  " + this.deltaIndexX + " deltaY = " + this.deltaIndexY); 
			} }
			if (board.tiles[indexX + deltaIndexX][indexY + deltaIndexY] == null
					&& (this.deltaIndexX + this.deltaIndexY) < 0  &&  indexX + deltaIndexX >=0 && indexY + deltaIndexY >= 0) { // x and Y <0
				while (board.tiles[indexX + deltaIndexX][indexY + deltaIndexY] == null
						&& deltaIndexX + deltaIndexY > (indexX * valX + indexY * valY) ) {
					this.deltaIndexX = (deltaIndexX - 1) * -valX;
					this.deltaIndexY = (deltaIndexY - 1) * -valY;
					System.out.println("deltaIndeX = " + deltaIndexX +" deltzIndeY = " + deltaIndexY);
					System.out.println("deltaX =  " + this.deltaIndexX + " deltaY = " + this.deltaIndexY);
				} 
			}

			/*if (board.tiles[indexX + deltaIndexX][indexY + deltaIndexY] == null
					&& (this.deltaIndexX + this.deltaIndexY) > 0) { // x et y > 0
				do {
					this.deltaIndexX = (deltaIndexX + 1) * valX;
					this.deltaIndexY = (deltaIndexY + 1) * valY;
					System.out.println("deltaX =  " + this.deltaIndexX + " deltaY = " + this.deltaIndexY);
				} while (board.tiles[indexX + deltaIndexX][indexY + deltaIndexY] == null
				 		&& deltaIndexX + deltaIndexY < 7 - (indexX * valX + indexY * valY));
			} 
			if (board.tiles[indexX + deltaIndexX][indexY + deltaIndexY] == null
					&& (this.deltaIndexX + this.deltaIndexY) < 0) { // x and Y <0
				do {
					this.deltaIndexX = (deltaIndexX - 1) * -valX;
					this.deltaIndexY = (deltaIndexY - 1) * -valY;
					System.out.println("deltaIndeX = " + deltaIndexX +" deltzIndeY = " + deltaIndexY);
					System.out.println("deltaX =  " + this.deltaIndexX + " deltaY = " + this.deltaIndexY);
				} while (board.tiles[indexX + deltaIndexX][indexY + deltaIndexY] == null
						&& deltaIndexX + deltaIndexY > (indexX * valX + indexY * valY) );
			} */
			System.out.println("avancée en X : " + this.deltaIndexX + "avancée en y" + this.deltaIndexY);
			System.out.println("break");
			this.deltaIndexX = valX;
			this.deltaIndexY = valY;
 
			// newIndexX = indexX + deltaIndexX * i;
			// newIndexY = indexY + deltaIndexY * j;
			if (board.tiles[indexX + deltaIndexX][indexY + deltaIndexY] != null) {
				// newIndexX = indexX + deltaIndexX;
				// newIndexY = indexY + deltaIndexY;
				switch (board.tiles[indexX + deltaIndexX][indexY + deltaIndexY].spriteType) {
				case StoneWall: // ok
					System.out.println("stone");

					this.deltaIndexX = valX;
					this.deltaIndexY = valY;
					System.out.println("delatIndexX= " + deltaIndexX);
					System.out.println("delatIndexY= " + deltaIndexY);
					break;
				case Gem: // to finish
					System.out.println("gem");
					this.deltaIndexX = -valX; // put back vectors
					this.deltaIndexY = -valY;
					
					//this.deltaIndexX = -this.deltaIndexX;
					//this.deltaIndexY = -this.deltaIndexY;
					
					
					
					System.out.println("deltaIndexX apres gem = " + deltaIndexX);
					System.out.println("deltaIndexY apres gem = " + deltaIndexY);
					break;
				case IceWall: // changer la position de l'image en hors plateau ou sur mur pierre droite
					System.out.println("ice");

					board.sprites.remove(board.tiles[indexX + deltaIndexX][indexY + deltaIndexY]); // ice[x](y] no mre in sprite list
																									
					board.tiles[indexX + deltaIndexX][indexY + deltaIndexY] = null; // case null
					board.paintComponent(board.getGraphics()); // force l'appel à la methode de redisseneer

					this.deltaIndexX = valX; // put back vectors
					this.deltaIndexY = valY;
					System.out.println("deltaIndexX= " + deltaIndexX);
					System.out.println("deltaIndexY= " + deltaIndexY);
					break; 
				case Turtle:
					System.out.println("turtle");
					// this.deltaIndexX = -this.deltaIndexX;
					// this.deltaIndexY = -this.deltaIndexY;		//not arrive to different cases with EnumNbPlayer 
					
					((Turtle) board.tiles[indexX+deltaIndexX][indexY+deltaIndexY]).reset(); //turtle with this position go to first position
					
					this.deltaIndexX = valX; // put back vectors
					this.deltaIndexY = valY;
					
					System.out.println("delatIndexX= " + deltaIndexX);
					System.out.println("delatIndexY= " + deltaIndexY);
					break;
				default:
					this.deltaIndexX = valX;
					this.deltaIndexY = valY;
				}
			} 
			break;
		}
	}
	/*
	 * for (int i = 1; i < 8 - indexX; i++) { for (int j = 1; j < 8 - indexY; j++) {
	 * System.out.println("pos tortue = " + board.tiles[indexX][indexY]);
	 * System.out.println("cases parcourues = " + board.tiles[indexX + deltaIndexX *
	 * i][indexY + deltaIndexY * j]); if (board.tiles[indexX + deltaIndexX *
	 * i][indexY + deltaIndexY * j] != null) { System.out.println("Blocked"); } if
	 * (board.tiles[indexX + deltaIndexX * i][indexY + deltaIndexY * j] == null) {
	 * break; } { switch (spriteType) { case StoneWall: break; case Turtle:
	 * this.deltaIndexX = -this.deltaIndexX; this.deltaIndexY = -this.deltaIndexY;
	 * case Gem: this.deltaIndexX = -this.deltaIndexX; this.deltaIndexY =
	 * -this.deltaIndexY; case IceWall: board.tiles[indexX + deltaIndexX * i][indexY
	 * + deltaIndexY * j] = null; } } } break; }
	 */

	public int getDeltaIndexX() {
		return deltaIndexX;
	}

	public void setDeltaIndexX(int deltaIndexX) {
		this.deltaIndexX = deltaIndexX;
	}

	public int getDeltaIndexY() {
		return deltaIndexY;
	}

	public void setDeltaIndexY(int deltaIndexY) {
		this.deltaIndexY = deltaIndexY;
	}

	public int getDeltaIndexZ() {
		return deltaIndexZ;
	}

	public void setDeltaIndexZ(int deltaIndexZ) {
		this.deltaIndexZ = deltaIndexZ;
	}
}