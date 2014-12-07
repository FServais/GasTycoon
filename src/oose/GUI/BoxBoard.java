package oose.GUI;

import oose.interfaces.CellInterface;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class BoxBoard extends JPanel {
	
	private BoxPanel boxTable[][];
    private int width, height;

	public BoxBoard(GUI gui) {
		super(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 0.5;
		
		CellInterface board[][] = gui.getGame().getBoard();
		width = board[1].length;
		height = board.length / width;
		
		BoardMouseListener m = new BoardMouseListener(gui);
		
		for(int i = 0; i < height; ++i) {
			for(int j = 0; j < width; ++j) {
				boxTable[i][j] = new BoxPanel(i, j, m);
				boxTable[i][j].setBoxImage(board[i][j]);
				c.gridy = i;
				c.gridx = j;
				add(boxTable[i][j], c);
			}
		}
	}

	public void update(CellInterface[][] board) {
		for(int i = 0; i < height; ++i) {
			for(int j = 0; j < width; ++j) {
				boxTable[i][j].setBoxImage(board[i][j]);
			}
		}
	}
}

