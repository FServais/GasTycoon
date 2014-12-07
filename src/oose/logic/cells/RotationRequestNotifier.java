package oose.logic.cells;

import oose.logic.Coord;
import oose.logic.exceptions.NoRotationRequestObserverException;

/**
 * Interface implemented by any cell of which should be observed
 * @author Servais Fabrice, Magera Floriane & Mormont Romain
 */
abstract class RotationRequestNotifier 
{
	RotationRequestObserver rro = null; /** The supply observer */
	
	/**
	 * Attach an observer to the observable cell
	 * @param so The supply observer
	 */
	public void attach(RotationRequestObserver rro)
	{
		this.rro = rro;
	}
	
	/**
	 * Notify the observer that the observed cell supply was updated
	 * @param supplied True if the observed cell is supplied, false otherwise
	 * @param cell_id The unique identifier of the updated fireplace cell, -1 if the cell is not a fireplace
	 * @throws NoRotationRequestObserverException Thrown when no observer were initialized
	 */
	public void notify_request(Coord c, boolean clockwise) throws NoRotationRequestObserverException
	{
		if(rro == null)
			throw new NoRotationRequestObserverException();
		
		rro.get_notified(c, clockwise);
	}
}
