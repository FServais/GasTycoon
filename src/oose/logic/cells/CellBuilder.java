package oose.logic.cells;

import oose.interfaces.Orientation;
import oose.interfaces.Piece;

/**
 * A class for building cells
 * @author Servais Fabrice, Magera Floriane & Mormont Romain
 */
public class CellBuilder 
{
	private static CellBuilder instance = null; /** singleton instance of the class */
	
	private int fireplace_counter; /** the counter of fireplace cells */
	private SupplyObserver so; /** the fireplace cell observer */ 
	
	/**
	 * Constructs a CellBuilder object
	 */
	private CellBuilder()
	{
		fireplace_counter = 0;
		so = null;
	}
	
	/**
	 * Get the singleton instance of the CellBuilder class
	 * @return The singleton instance of CellBuilder
	 */
	public static CellBuilder get_instance()
	{
		if(instance == null)
			instance = new CellBuilder();
		return instance;
	}
	
	/**
	 * Count the number of generated fireplace
	 * @return The number of fireplace
	 */
	public int nb_fireplace()
	{
		return fireplace_counter;
	}
	
	/**
	 * Initialize the supply observer of the fireplace cell
	 * @param The supply observer
	 * Must be called before any cell creation
	 */
	public void set_supply_observer(SupplyObserver so)
	{
		this.so = so;
	}
	
	/**
	 * Build a cell
	 * @param o The orientation of the piece
	 * @param p The piece type
	 * @return The brand new cell
	 */
	public Cell build_cell(Orientation o, Piece p)
	{
		Cell c = null;
		
		switch(p)
		{
		case EMPTY:
			c = new EmptyCell(o,p);
			break;
		case PIPELINE_ANGLED:
		case PIPELINE_T:
		case PIPELINE:
			c = new PipelineCell(o,p);
			break;
		case FIREPLACE:
			FireplaceCell fc = new FireplaceCell(o,p,fireplace_counter++);
			fc.attach(so); // attach the supply observer
			c = fc;
			break;
		case GAS_ANGLED:
		case GAS_T:
		case GAS:
			c = new GasCell(o,p);
		}
		
		return c;
	}
}
