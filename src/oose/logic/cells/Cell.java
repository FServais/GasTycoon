package oose.logic.cells;

import java.util.Vector;

import oose.interfaces.CellInterface;
import oose.interfaces.Orientation;
import oose.interfaces.Piece;

/**
 * Class representing a cell of the board
 * @author Servais Fabrice, Magera Floriane & Mormont Romain
 */
public class Cell implements CellInterface 
{
	Orientation orientation; /** cell orientation */
	Piece piece; /** cell piece */
	Cell[] neighbors = null;
	boolean supplied = false; 
	boolean[] connections = null;
	
	/**
	 * Initialize cell members
	 */
	private Cell()
	{
		supplied = false;
		connections = new boolean[4];
		
		for(int i = 0; i < 4; ++i)
			connections[i] = false;
	}
	
	/**
	 * Builds a cell 
	 * @param o The cell orientation
	 * @param p The cell piece
	 */
	public Cell(Orientation o, Piece p)
	{
		this();
		orientation = o;
		piece = p;
	}
	
	@Override
	public Piece getPieceType() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orientation getOrientation() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSupplied() 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clockwiseNext() 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void counterClockwiseNext() 
	{
		// TODO Auto-generated method stub

	}
	
	/**
	 * Rotate the cell int the given die
	 * @param clockwise
	 */
	public void rotate(boolean clockwise) 
	{

	}
	
	/**
	 * Set the current cell neighbors
	 * @param neighbors The neighbors. They must be stored in the following order [top, right, bottom, left]
	 */
	public void set_neighbors(Cell[] neighbors)
	{
		this.neighbors = neighbors;
	}
}
