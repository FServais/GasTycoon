package oose.logic.cells;

import oose.interfaces.Orientation;
import oose.interfaces.Piece;
import oose.logic.Coord;
import oose.logic.exceptions.BadIdException;

/**
 * A class for building cells
 * @author Servais Fabrice, Magera Floriane & Mormont Romain
 */
public class CellBuilder 
{
	private static CellBuilder instance = null; /** singleton instance of the class */
	private RotationRequestObserver rro; /** the fireplace cell observer */ 
	
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
	 * Initialize the supply observer of the fireplace cell
	 * @param The supply observer
	 * Must be called before any cell creation
	 */
	public void set_supply_observer(RotationRequestObserver rro)
	{
		this.rro = rro;
	}
	
	/**
	 * Build a cell
	 * @param o The orientation of the piece
	 * @param p The piece type
	 * @param row The row index
	 * @param col The col index
	 * @return The brand new cell
	 */
	public Cell build_cell(Orientation o, Piece p, int row, int col)
	{
		Cell cell = new Cell(o,p);
		cell.set_coord(new Coord(row, col));
		cell.attach(rro); // attach the observer
		return cell;
	}
	
	/**
	 * Create a cell based on the piece id and orientation id
	 * @param orient_id The orientation id
	 * @param piece_id The piece id
	 * @param row The row index
	 * @param col The col index
	 * @return The brand new cell
	 * @throws BadIdException Thrown if one the given id is invalid
	 */
	public Cell build_cell(int orient_id, int piece_id, int row, int col) throws BadIdException
	{
		return build_cell(get_orient(orient_id), get_piece(piece_id), row, col);
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
