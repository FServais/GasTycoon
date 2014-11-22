/**
 * This interface contains the common methods to get information about a cell of GasTycoon board.
 */
public interface CellInterface {

	/**
	 * Get the piece type.
	 * @return The piece type.
	 */
	public Piece getPieceType();
	
	/**
	 * Get the piece orientation.
	 * @return The piece orientation.
	 */
	public Orientation getOrientation();
	
	/**
	 * Check if the piece is supplied
	 * @return True if the piece is supplied, false otherwise.
	 */
	public boolean isSupplied();
	
	/**
	 * Rotate the cell clockwise.
	 */
	public void clockwiseNext();
	
	/**
	 * Rotate the cell counterclockwise.
	 */
	public void counterClockwiseNext();
}
