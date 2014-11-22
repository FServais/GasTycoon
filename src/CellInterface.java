/**
 * This interface contains the common methods to get information about a cell of the game.
 */

public interface CellInterface {
	/**
	 * Get the type of the piece.
	 * 
	 * @return Type of the piece.
	 */
	public Piece getPieceType();
	
	/**
	 * Get the orientation of the piece.
	 * 
	 * @return Orientation of the piece.
	 */
	public Orientation getOrientation();
	
	/**
	 * Check if the piece is supplied.
	 * 
	 * @return True if the piece if supplied, false otherwise.
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
