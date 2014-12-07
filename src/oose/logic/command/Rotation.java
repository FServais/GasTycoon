package oose.logic.command;

import oose.logic.Board;

/**
 * A class implementing the rotation of cell
 * @author Servais Fabrice, Magera Floriane & Mormont Romain
 */
public class Rotation implements Command 
{
	int row, col; /** row and column to rotate */
	boolean clockwise; /** the rotation direction */
	Board board = null; /** The game board */
	
	/**
	 * Create a rotation command
	 * @param row The index of row of the cell to rotate in the board
	 * @param col The index of column of the cell to rotate in the board
	 * @param clockwise The rotation direction
	 * @param board The game board
	 */
	public Rotation(int row, int col, boolean clockwise, Board board)
	{
		this.row = row;
		this.col = col;
		this.clockwise = clockwise;
		this.board = board;
	}
	
	@Override
	public void execute() 
	{
		board.rotate(row,col,clockwise);
	}

	@Override
	public void revert() 
	{
		board.rotate(row,col,!clockwise);
	}
}
