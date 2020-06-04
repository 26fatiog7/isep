package TUILE;
import javax.swing.ImageIcon;
import JEU.Board;
import JEU.EnumSpriteType;
import JEU.EnumWallType;

public class Wood extends Sprite {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Board board;

	public Wood(Board board, int indexX, int indexY) {
		super(board, indexX, indexY);
		this.spriteType = EnumSpriteType.Wood;
		this.imgSprite = (new ImageIcon(getClass().getResource("/image/Wood.png"))).getImage();
		this.setPosition(indexX, indexY);
	}
}  