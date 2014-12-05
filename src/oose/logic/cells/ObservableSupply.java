package oose.logic.cells;

import oose.logic.exceptions.NoSupplyObserverException;

/**
 * Interface implemented by any cell of which the supply can be observed
 * @author Servais Fabrice, Magera Floriane & Mormont Romain
 */
public interface ObservableSupply 
{
	SupplyObserver so = null; /** The supply observer */
	
	/**
	 * Attach an observer to the observable cell
	 * @param so The supply observer
	 */
	public void attach(SupplyObserver so);
	
	/**
	 * Notify the observer that the observed cell supply was updated
	 * @param supplied True if the observed cell is supplied, false otherwise
	 * @param cell_id The unique identifier of the updated cell
	 * @throws NoSupplyObserverException Thrown when no supply observer were initialized
	 */
	public void notify_supply(boolean supplied, int cell_id) throws NoSupplyObserverException;
}
