package JEU;

import TUILE.Sprite;

import TUILE.Tile;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Board extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final int columnsCount = 8;
	public final int rowsCount = 8;
	public int tileWidth;
	public int tileHeight;
	public ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	public Sprite[][] tiles;
	public BoardFrame boardFrame;

	public Board() {
		super();
		this.tiles = new Sprite[rowsCount][columnsCount];
		this.setVisible(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		tileWidth = (getWidth() - 1) / columnsCount;
		tileHeight = (getHeight() - 1) / rowsCount;
		System.out.println("W=" + getWidth() + " H= " + getHeight() + " tw= " + tileWidth);
		g.setColor(Color.red);
		for (int i = 0; i <= columnsCount; i++) { 
			g.drawLine((int) Math.round(tileWidth * i), 0, (int) Math.round(tileWidth * i), getHeight() - 1);
		}
		for (int i = 0; i <= rowsCount; i++) {
			g.drawLine(0, (int) Math.round(tileHeight * i), (getWidth() - 1), (int) Math.round(tileHeight * i));
		}
		Sprite sprite;
		Graphics2D g2d = (Graphics2D) g;
		Graphics g2 = (Graphics2D) g;
		for (int i = 0; i < sprites.size(); i++) {
		//	System.out.println("Drawing sprite nb " + i);
		 	sprite = sprites.get(i);
			g2.drawImage(sprite.imgSprite, sprite.indexX * tileWidth + 3, sprite.indexY * tileHeight + 3, tileWidth - 5,
		 	 		tileHeight - 5, this);
			//System.out.println("nb ligne/col = " + columnsCount +"/"+ rowsCount);
			//System.out.println("largeur/longueur case = " + tileWidth + "/" + tileHeight);
		
		/*AffineTransform rotation = new AffineTransform();
		rotation = AffineTransform.getRotateInstance(90,(int)(tileWidth - 5, tileHeight - 5);
		g2d.drawImage(sprite.imgSprite, rotation, null);*/
		}
	}
}