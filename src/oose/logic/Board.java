package oose.logic;

import java.util.Vector;

import oose.interfaces.CellInterface;
import oose.logic.cells.Cell;
import oose.logic.cells.GasCell;

public class Board 
{
	private Cell[][] board; // board internal representation
	private Vector<Cell> gas;
	
	/**
	 * Construct a board
	 * @param board A cell array
	 */
	public Board(Cell[][] board)
	{
		this.board = board;
		set_neighbors();
		
		gas = new Vector<Cell>();
		for(Cell[] row : board){
			for(Cell c : row){
				if(c instanceof GasCell)
					gas.add(c);
			}
		}
	}
	
	/**
	 * Initialize the neighbors of all the cells in the board
	 */
	private void set_neighbors()
	{
		for(int i = 0 ; i < board.length ; ++i){
			for(int j = 0 ; j < board[i].length ; ++j){
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
				c.set_supplied(false);
		
		for(Cell c : gas)
				c.update_supplied();
		
	}
	
	/**
	 * 
	 */
	public void rotate(int i, int j, boolean clockwise)
	{
		board[i][j].rotate(clockwise);
	}

	public CellInterface[][] get_array() {
		// TODO Auto-generated method stub
		return null;
	}
}
