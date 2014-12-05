package oose.logic.cells;

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
	
	/**
	 * Builds a cell 
	 * @param o The cell orientation
	 * @param p The cell piece
	 */
	public Cell(Orientation o, Piece p)
	{
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

}
