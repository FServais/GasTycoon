package oose.logic.cells;

import oose.interfaces.Orientation;
import oose.interfaces.Piece;
import oose.logic.exceptions.BadIdException;

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
	 * Reset the object (for creating cells for a new board)
	 */
	public void reset()
	{
		fireplace_counter = 0;
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
	
	/**
	 * Create a cell based on the piece id and orientation id
	 * @param orient_id The orientation id
	 * @param piece_id The piece id
	 * @return The brand new cell
	 * @throws BadIdException Thrown if one the given id is invalid
	 */
	public Cell build_cell(int orient_id, int piece_id) throws BadIdException
	{
		return build_cell(get_orient(orient_id), get_piece(piece_id));
	}
	
	/**
	 * Return the orientation for the given orientation id
	 * @param orient_id The orientation id
	 * @return The orientation
	 * @throws BadIdException Thrown if the id is invalid
	 */
	public Orientation get_orient(int orient_id) throws BadIdException
	{
		switch(orient_id)
		{
		case 0: return Orientation.UP;
		case 1: return Orientation.RIGHT;
		case 2: return Orientation.DOWN;
		case 3: return Orientation.LEFT;
		default:
			throw new BadIdException("Bad orientation id");
		}
	}
	
	/**
	 * Return a piece based on the piece id
	 * @param piece_id The piece id
	 * @return The piece
	 * @throws BadIdException Thrown if the id is invalid
	 */
	public Piece get_piece(int piece_id) throws BadIdException
	{
		switch(piece_id)
		{
		case 0: return Piece.EMPTY;
		case 1: return Piece.PIPELINE_ANGLED;
		case 2: return Piece.PIPELINE_T;
		case 3: return Piece.PIPELINE;
		case 4: return Piece.FIREPLACE;
		case 5: return Piece.GAS;
		case 6: return Piece.GAS_T;
		case 7: return Piece.GAS_ANGLED;
		default:
			throw new BadIdException("Bad piece id");
		}
	}
}
