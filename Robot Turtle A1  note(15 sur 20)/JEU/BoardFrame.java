package JEU;

import javax.swing.JFrame;
import JEU.Board;

public class BoardFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BoardFrame(Board board) {
		this.setTitle("JEU Robot Turtle");
		this.setSize(830, 830);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(board);
		board.boardFrame = this;
		this.setVisible(true);
	}
}