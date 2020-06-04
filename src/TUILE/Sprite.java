package TUILE;

import java.awt.Image;

import JEU.Board;
import JEU.EnumSpriteType;

public class Sprite {
	protected Board board;
	protected EnumSpriteType spriteType;
	public int indexX;
	public int indexY;
	public Image imgSprite;
	
	public Sprite(Board board, int indexX, int indexY) {  //tile MUST be cleared before creating new sprite
		super(); 
		this.board = board;
		this.board.sprites.add(this);
		this.setPosition(indexX, indexY);
	} 
	
	public int getIndexX() {		// pas l'impression qu'ils soient utilisés ???
		return indexX;
	}

	public void setIndexX(int indexX) {
		this.indexX = indexX;
	}

	public int getIndexY() {
		return indexY;
	}

	public void setIndexY(int indexY) {
		this.indexY = indexY;
	}

	protected void setPosition(int newIndexX, int newIndexY)  {
		board.tiles[this.indexX][this.indexY] = null;
		this.indexX = newIndexX;
		this.indexY = newIndexY;
		board.tiles[this.indexX][this.indexY] = this;
		board.updateUI();
	}
}