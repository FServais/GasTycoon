package oose.logic.cells;

/**
 * An interface implemented by observers of cells/pieces
 * @author Servais Fabrice, Magera Floriane & Mormont Romain
 */
public interface PieceObserver 
{
	/**
	 * Update the observer state on a modification of supply of a cell
	 * @param supplied True if the observed cell is supplied, false otherwise
	 * @param cell_id The unique identifier of the updated fireplace cell, -1 if the cell is not a fireplace
	 */
	public void update_supply(boolean supplied, int cell_id);

	/**
	 * Update the observer state on a rotation of a cell
	 */
	public void update_rotate();
}
