package oose.logic;

import java.util.Vector;

import oose.interfaces.CellInterface;
import oose.logic.cells.Cell;

public class Board 
{
	private Cell[][] board; // board internal representation
	private Vector<Cell> gases;
	private Vector<Cell> fireplaces;
	
	/**
	 * Construct a board
	 * @param board A cell array
	 */
	public Board(Cell[][] board)
	{
		this.board = board;
		
		gases = new Vector<Cell>();
		fireplaces = new Vector<Cell>();
		
		// set the neigbords of the cells
		set_neighbors();
		
		// store gas and fireplace cells
		for(Cell[] row : board)
		{
			for(Cell c : row)
			{
				if(c.is_fireplace())
					fireplaces.add(c);
				else if(c.is_gas())
					gases.add(c);
			}
		}
	}
	
	// Temp
	public Vector<Cell> get_fireplaces(){
		return fireplaces;
	}
	
	/**
	 * Initialize the neighbors of all the cells in the board
	 */
	private void set_neighbors()
	{
		for(int i = 0 ; i < board.length ; ++i)
		{
			for(int j = 0 ; j < board[i].length ; ++j)
			{
				Cell[] neighb = new Cell[4];
				// Top
				if(i == 0)
					neighb[0] = null;
				else
					neighb[0] = board[i-1][j];
				
				// Bottom
				if(i == board.length-1)
					neighb[2] = null;
				else
					neighb[2] = board[i+1][j];
				
				// Left
				if(j == 0)
					neighb[3] = null;
				else
					neighb[3] = board[i][j-1];
				
				// Right
				if(j == board[i].length-1)
					neighb[1] = null;
				else
					neighb[1] = board[i][j+1];
				
				board[i][j].set_neighbors(neighb);
			}
		}
	}

	
	/**
	 * Update the supply flag of all the cells
	 */
	private void update_supply()
	{
		// Set all supply variable to false
		for(Cell[] row : board)
			for(Cell c : row)
				c.set_unsupplied();
		
		// propagate supply from the gas cell
		for(Cell c : gases)
			c.start_supply();
	}
	
	/**
	 * Perform a clockwise rotation of the cell at position (i,j) if clockwise is true, 
	 * a counter-clockwise rotation otherwise
	 */
	public void rotate(int i, int j, boolean clockwise)
	{
		board[i][j].rotate(clockwise);
		update_supply();
	}

	/**
	 * Return the cell board
	 * @return THe cell board
	 */
	public CellInterface[][] get_array() 
	{	
		return board;
	}
	
	/**
	 * Count the number of non empty cell in the board
	 * @return The number of non empty cell 
	 */
	public int count_non_empty_cells()
	{
		int count = 0;
		
		for(Cell[] row : board)
			for(Cell c : row)
				if(!c.is_empty())
					count++;
		
		return count;	
	}

	/**
	 * Check whether all the fireplace are supplied
	 * @return True if all the fireplace are supplied, false otherwise
	 */
	public boolean are_fireplace_supplied() 
	{	
		boolean supplied = true;
		
		for(Cell c : fireplaces)
			supplied &= c.isSupplied();
		
		return supplied;
	}
}
