package TUILE;

import javax.swing.ImageIcon;
import JEU.Board;
import JEU.EnumSpriteType;

public class Gem extends Sprite {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Board board;

	public Gem(Board board, int indexX, int indexY) {
		super(board, indexX, indexY);
		this.spriteType = EnumSpriteType.Gem;
		this.imgSprite = (new ImageIcon(getClass().getResource("/image/joyaurouge.png"))).getImage();
		this.setPosition(indexX, indexY);
	}
}
