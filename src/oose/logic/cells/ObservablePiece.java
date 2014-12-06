package oose.logic.cells;

import oose.logic.exceptions.NoPieceObserverException;

/**
 * Interface implemented by any cell of which should be observed
 * @author Servais Fabrice, Magera Floriane & Mormont Romain
 */
abstract class ObservablePiece 
{
	PieceObserver so = null; /** The supply observer */
	
	/**
	 * Attach an observer to the observable cell
	 * @param so The supply observer
	 */
	public void attach(PieceObserver so)
	{
		this.so = so;
	}
	
	/**
	 * Notify the observer that the observed cell supply was updated
	 * @param supplied True if the observed cell is supplied, false otherwise
	 * @param cell_id The unique identifier of the updated fireplace cell, -1 if the cell is not a fireplace
	 * @throws NoPieceObserverException Thrown when no observer were initialized
	 */
	public void notify_supply(boolean supplied, int cell_id) throws NoPieceObserverException
	{
		if(so == null)
			throw new NoPieceObserverException();
		
		so.update_supply(supplied, cell_id);
	}
	
	/**
	 * Notify the observer that a cell was rotated
	 * @throws NoPieceObserverException Thrown when no observer were initialized
	 */
	public void notify_rotation() throws NoPieceObserverException
	{
		if(so == null)
			throw new NoPieceObserverException();
		
		so.update_rotate();
	}
	
}
