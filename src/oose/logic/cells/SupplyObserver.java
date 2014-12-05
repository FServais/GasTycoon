package oose.logic.cells;

/**
 * An interface implemented by observers of cells' supply
 * @author Servais Fabrice, Magera Floriane & Mormont Romain
 */
public interface SupplyObserver 
{
	/**
	 * Update the observer state
	 * @param supplied True if the observed cell is supplied, false otherwise
	 * @param cell_id The unique identifier of the updated cell
	 */
	public void update_supply(boolean supplied, int cell_id);
}
