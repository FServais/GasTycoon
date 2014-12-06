package oose.logic.cells;

import oose.interfaces.Orientation;
import oose.interfaces.Piece;

public class GasCell extends Cell 
{
	public GasCell(Orientation o, Piece p)
	{
		super(o,p);
		connections[0] = has_top_link();
		connections[1] = has_right_link();
		connections[2] = has_bottom_link();
		connections[3] = has_left_link();
	}

	protected boolean has_top_link() {
		return (piece == Piece.GAS && (orientation == Orientation.UP)) || 
				(piece == Piece.GAS_T && (orientation == Orientation.UP || orientation == Orientation.RIGHT || orientation == Orientation.LEFT) ||
				(piece == Piece.GAS_ANGLED && (orientation == Orientation.UP || orientation == Orientation.LEFT)));
	}

	protected boolean has_right_link() {
		return (piece == Piece.GAS && (orientation == Orientation.RIGHT)) || 
				(piece == Piece.GAS_T && (orientation == Orientation.UP || orientation == Orientation.RIGHT || orientation == Orientation.DOWN) ||
				(piece == Piece.GAS_ANGLED && (orientation == Orientation.UP || orientation == Orientation.RIGHT)));
	}

	protected boolean has_bottom_link() {
		return (piece == Piece.GAS && (orientation == Orientation.DOWN)) || 
				(piece == Piece.GAS_T && (orientation == Orientation.LEFT || orientation == Orientation.RIGHT || orientation == Orientation.DOWN) ||
				(piece == Piece.GAS_ANGLED && (orientation == Orientation.DOWN || orientation == Orientation.RIGHT)));
	}

	protected boolean has_left_link() {
		return (piece == Piece.GAS && (orientation == Orientation.LEFT)) || 
				(piece == Piece.GAS_T && (orientation == Orientation.UP || orientation == Orientation.LEFT || orientation == Orientation.DOWN) ||
				(piece == Piece.GAS_ANGLED && (orientation == Orientation.DOWN || orientation == Orientation.RIGHT)));
	}
}
