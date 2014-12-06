package oose.logic.cells;

import oose.interfaces.Orientation;
import oose.interfaces.Piece;

public class EmptyCell extends Cell 
{
	public EmptyCell(Orientation o, Piece p)
	{
		super(o,p);
		connections[0] = has_top_link();
		connections[1] = has_right_link();
		connections[2] = has_bottom_link();
		connections[3] = has_left_link();
	}

	@Override
	protected boolean has_top_link() { return false; }

	@Override
	protected boolean has_right_link() { return false; }

	@Override
	protected boolean has_bottom_link() { return false; }

	@Override
	protected boolean has_left_link() { return false; }
}
