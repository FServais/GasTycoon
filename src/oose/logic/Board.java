package oose.logic;

import oose.logic.cells.Cell;

public class Board 
{
	private Cell[][] board; // board internal representation
	
	/**
	 * Construct a board
	 * @param board A cell array
	 */
	public Board(Cell[][] board)
	{
		this.board = board;
		set_neighbors();
	}
	
	/**
	 * Initialize the neighbors of all the cells in the board
	 */
	private void set_neighbors()
	{
		
	}
	
	/**
	 * Update the supply flag of all the cells
	 */
	private void update_supply()
	{
		
	}
	
	/**
	 * 
	 */
	public void rotate(int i, int j, boolean clockwise)
	{
		board[i][j].rotate(clockwise);
	}
}
