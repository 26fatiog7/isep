package TUILE;
import javax.swing.ImageIcon;
import JEU.Board;
import JEU.EnumSpriteType;
import JEU.EnumWallType;

public class Wall extends Sprite {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Board board;

	public Wall(Board board, int indexX, int indexY, EnumWallType wallType) {
		super(board, indexX, indexY);
		this.spriteType = (wallType == EnumWallType.Stone) ? EnumSpriteType.StoneWall : EnumSpriteType.IceWall;
		this.imgSprite = (new ImageIcon(getClass().getResource("/image/"+((wallType == EnumWallType.Stone) ?"WALL.png" : "ICE.png")))).getImage();
		this.setPosition(indexX, indexY);
	}
	public void moveIce() {
		int newIndexX;
		int newIndexY;
		
		newIndexX = 7;
		newIndexY = 7;
		
		this.setPosition(newIndexX, newIndexY);
	}
}  