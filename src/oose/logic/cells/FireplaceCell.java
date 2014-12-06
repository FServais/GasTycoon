package oose.logic.cells;

import oose.interfaces.Orientation;
import oose.interfaces.Piece;

public class FireplaceCell extends Cell
{
	private int identifier = -1;
	
	/**
	 * Construct a fireplace cell
	 * @param o The fireplace orientation
	 * @param p The piece
	 * @param identifier The fireplace identifier (must be unique among all fireplace identifiers)
	 */
	public FireplaceCell(Orientation o, Piece p, int identifier)
	{
		super(o, p);
		this.identifier = identifier;
	}
	
	/**
	 * Return the fireplace identifier
	 * @return The fireplace identifier
	 */
	public int get_id()
	{
		return identifier;
	}
}
