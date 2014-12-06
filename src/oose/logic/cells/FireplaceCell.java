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
		connections[0] = has_top_link();
		connections[1] = has_right_link();
		connections[2] = has_bottom_link();
		connections[3] = has_left_link();
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
	
	protected boolean has_top_link(){
		return orientation == Orientation.UP;
	}
	
	protected boolean has_right_link(){
		return orientation == Orientation.RIGHT;
	}
	
	protected boolean has_bottom_link(){
		return orientation == Orientation.DOWN;
	}
	
	protected boolean has_left_link(){
		return orientation == Orientation.LEFT;
	}
}
