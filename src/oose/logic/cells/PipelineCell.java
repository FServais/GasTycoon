package oose.logic.cells;

import oose.interfaces.Orientation;
import oose.interfaces.Piece;

public class PipelineCell extends Cell 
{
	public PipelineCell(Orientation o, Piece p)
	{
		super(o,p);
		connections[0] = has_top_link();
		connections[1] = has_right_link();
		connections[2] = has_bottom_link();
		connections[3] = has_left_link();
	}

	@Override
	protected boolean has_top_link() {
		return (piece == Piece.PIPELINE_ANGLED && (orientation == Orientation.UP || orientation == Orientation.LEFT)) || 
				(piece == Piece.PIPELINE_T && (orientation == Orientation.UP || orientation == Orientation.RIGHT || orientation == Orientation.LEFT) ||
				(piece == Piece.PIPELINE && (orientation == Orientation.RIGHT)));
	}

	@Override
	protected boolean has_right_link() {
		return (piece == Piece.PIPELINE_ANGLED && (orientation == Orientation.UP || orientation == Orientation.RIGHT)) || 
				(piece == Piece.PIPELINE_T && (orientation == Orientation.UP || orientation == Orientation.RIGHT || orientation == Orientation.DOWN) ||
				(piece == Piece.PIPELINE && (orientation == Orientation.UP)));
	}

	@Override
	protected boolean has_bottom_link() {
		return (piece == Piece.PIPELINE_ANGLED && (orientation == Orientation.RIGHT || orientation == Orientation.DOWN)) || 
				(piece == Piece.PIPELINE_T && (orientation == Orientation.DOWN || orientation == Orientation.RIGHT || orientation == Orientation.LEFT) ||
				(piece == Piece.PIPELINE && (orientation == Orientation.RIGHT)));
	}

	@Override
	protected boolean has_left_link() {
		return (piece == Piece.PIPELINE_ANGLED && (orientation == Orientation.DOWN || orientation == Orientation.LEFT)) || 
				(piece == Piece.PIPELINE_T && (orientation == Orientation.UP || orientation == Orientation.LEFT || orientation == Orientation.DOWN) ||
				(piece == Piece.PIPELINE && (orientation == Orientation.UP)));
	}
}
