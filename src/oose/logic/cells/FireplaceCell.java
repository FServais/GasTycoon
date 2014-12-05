package oose.logic.cells;

import oose.interfaces.Orientation;
import oose.interfaces.Piece;
import oose.logic.exceptions.NoSupplyObserverException;

public class FireplaceCell extends Cell implements ObservableSupply 
{
	
	private SupplyObserver so = null; /** supply observer : */
	private int identifier = -1;
	
	/**
	 * Construct a fireplace cell
	 * @param o The fireplace orientation
	 * @param p The piece
	 * @param identifier The fireplace identifier (must be unique among all fireplace identifiers)
	 */
	public FireplaceCell(Orientation o, Piece p, int identifier)
	{
		super(o, p);
		this.identifier = identifier;
	}
	
	/**
	 * Return the fireplace identifier
	 * @return The fireplace identifier
	 */
	public int get_id()
	{
		return identifier;
	}
	
	@Override
	public void attach(SupplyObserver so) 
	{
		this.so = so;
	}

	@Override
	public void notify_supply(boolean supplied, int cell_id) throws NoSupplyObserverException 
	{
		if(so == null)
			throw new NoSupplyObserverException("no observer set");
		so.update_supply(supplied, cell_id);
	}

}
