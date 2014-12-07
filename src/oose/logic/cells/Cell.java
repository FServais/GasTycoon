package oose.logic.cells;

import oose.interfaces.*;
import oose.logic.Coord;
import oose.logic.exceptions.BadSideIdException;
import oose.logic.exceptions.NoRotationRequestObserverException;

/**
 * Class representing a cell of the board
 * @author Servais Fabrice, Magera Floriane & Mormont Romain
 */
public class Cell extends RotationRequestNotifier implements CellInterface
{
	private Orientation orientation; /** cell orientation */
	private Piece piece; /** cell piece */
	private Cell[] neighbors = null; /** array of neighbors : [top right bottom left]*/
	private boolean supplied = false;  /** true if the cell is supplied */
	private boolean[] connections = null; /** array specifying if the current cell can 
											connect to its neighbours (top right bottom left)  */
	private Coord coordinates = null; // the coordinates of the cell
	
	/**
	 * Initialize cell members
	 */
	private Cell()
	{
		connections = new boolean[4];
		neighbors = new Cell[4];
		supplied = false;
	}
	
	/**
	 * Builds a cell 
	 * @param o The cell orientation
	 * @param p The cell piece
	 */
	public Cell(Orientation o, Piece p)
	{
		this();
		// warning : these must be set before calling has_***_link() 
		orientation = o;
		piece = p;

		// initialize connections array
		set_connections();
	}
	
	/**
	 * Set the coordinated of the cell
	 * @param coord The coordinates
	 */
	public void set_coord(Coord coord)
	{
		coordinates = coord;
	}
	
	/**
	 * Set the current cell neighbors
	 * @param neighbors The neighbors. They must be stored in the following order [top, right, bottom, left]
	 */
	public void set_neighbors(Cell[] neighbors)
	{
		this.neighbors = neighbors;
	}
	
	/**
	 * Initialize the connection array (piece and orientation must be set)
	 */
	private void set_connections()
	{
		// pre fill with false
		for(int i = 0; i < 4; ++i)
			connections[i] = false;
		
		// find the array for an UP orientation
		switch(piece)
		{
		case FIREPLACE:
		case GAS:
			connections[0] = true;
			break;
			
		case GAS_ANGLED:
		case PIPELINE_ANGLED:
			connections[0] = true;
			connections[1] = true;
			break;
			
		case GAS_T:
		case PIPELINE_T:
			connections[0] = true;
			connections[1] = true;
			connections[3] = true;
			break;
			
		case PIPELINE:
			connections[1] = true;
			connections[3] = true;
			break;
			
		default: // Empty piece don't need any rotation
			return;
		}
		
		// shift the array as many time as possible in order for it to match the cell orientation
		switch(orientation)
		{
		case LEFT:
			circular_shift(connections, false);
			break;
		case DOWN:
			circular_shift(connections, true);
			circular_shift(connections, true);
			break;
		case RIGHT:
			circular_shift(connections, true);
			break;
		default: // UP orientation is ok
			return;
		}
	}
	
	@Override
	public Piece getPieceType() 
	{
		return piece;
	}

	@Override
	public Orientation getOrientation() 
	{
		return orientation;
	}

	@Override
	public boolean isSupplied() 
	{
		return supplied;
	}
	
	/**
	 * Set the cell unsupplied
	 */
	public void set_unsupplied()
	{
		if(is_gas()) return; // a gas cell is always supplied
		supplied = false;
	}
	
	/**
	 * Notify the cell that it is supplied from the given side and propagate to its 
	 * possible connected unsupplied  neighbors
	 * @param side The side from which the supply is coming
	 */
	private void receive_supply(int side)
	{
		try 
		{
			if(this.piece == Piece.PIPELINE)
			{
				if(supplied)
					System.out.println("This is supplied");
			}
			if(supplied) // cell is already supplied => there is a cycle and we can stop
				return;
			
			supplied = true; // the cell is now supplied
			
			// run through the neigbords to find if some of them are linked with the current cell
			for(int i = 0 ; i < 4 ; ++i)
				if(i != side && has_link_with(i)) // exclude the direction from which the supply came
					neighbors[i].receive_supply((i + 2) % 4);
				
		} 
		catch (BadSideIdException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Notify a gas cell that it has to supply all the cells directly or indirectly connected to it
	 */
	public void start_supply()
	{
		if(!is_gas()) return;
		
		try 
		{
			for(int i = 0 ; i < 4 ; ++i)
				if(has_link_with(i)) // exclude the direction from which the supply came
					neighbors[i].receive_supply((i + 2) % 4);
		} 
		catch (BadSideIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	@Override
	public void clockwiseNext() 
	{
		// notify the logic that the cell must be rotated clockwise
		try {
			notify_request(coordinates, true);
		} catch (NoRotationRequestObserverException e) {
			System.err.println("No observer");
			e.printStackTrace();
		}
	}

	@Override
	public void counterClockwiseNext() 
	{
		// notify the logic that the cell must be rotated counterclockwise
		try {
			notify_request(coordinates, false);
		} catch (NoRotationRequestObserverException e) {
			System.err.println("No observer");
			e.printStackTrace();
		}
	}
	
	/**
	 * Rotate the cell in the given direction
	 * This function does not update the supply
	 * @param clockwise The rotation direction
	 */
	public void rotate(boolean clockwise) 
	{
		circular_shift(connections, clockwise);
		orientation = next(orientation, clockwise);
	}
	
	/**
	 * Find the orientation resulting from the rotation in the given direction
	 * @param o The current orientation
	 * @param clockwise The rotation direction
	 * @return The resulting orientation
	 */
	private static Orientation next(Orientation o, boolean clockwise)
	{
		switch(o)
		{
		case UP:
			return clockwise ? Orientation.RIGHT : Orientation.LEFT;
		case LEFT:
			return clockwise ? Orientation.UP : Orientation.DOWN;
		case DOWN:
			return clockwise ? Orientation.LEFT : Orientation.RIGHT;
		default: // case RIGHT
			return clockwise ? Orientation.DOWN : Orientation.UP;
		}
	}
	
	/**
	 * Perform a circular shift of an array
	 * @param array The array to shift
	 * @param right True for shifting the array to the right, false for shifting it to the left
	 */
	private static void circular_shift(boolean[] array, boolean right)
	{
		boolean saved_value, tmp;
		int i;
		
		saved_value = right ? array[array.length - 1] : array[0];
		i = right ? 0 : array.length - 1;
		
		while(i < array.length && i >= 0)
		{
			tmp = array[i];
			array[i] = saved_value;
			saved_value = tmp;
			
			if(right) ++i;
			else --i;
		}
	}

	/**
	 * Check if the cell have a link with another cell situated on the given side, 
	 * side ids are {0 : top, 1 : right, 2 : bottom, 3 : left}.
	 * @param side Side of the cell to check
	 * @return True if there is a link, false otherwise.
	 * @throws BadSideIdException If the side id is invalid
	 */
	private boolean has_link_with(int side) throws BadSideIdException
	{
		if(side < 0 || side >= 4)
			throw new BadSideIdException("should be between 0 and 4");
		
		if(neighbors[side] == null) // no neigbors on this side
			return false;
		
		return connections[side] && neighbors[side].has_connection((side + 2) % 4);
	}
	
	/**
	 * Return true if the cell has a connection on the given side
	 * @param side The side  ({0 : top, 1 : right, 2 : bottom, 3 : left})
	 * @return True if the cell has a connection
	 */
	private boolean has_connection(int side)
	{
		return connections[side];
	}

	/**
	 * Check whether the cell is a fireplace
	 * @return True if the cell is a fireplace
	 */
	public boolean is_fireplace() 
	{
		return piece == Piece.FIREPLACE;
	}
	
	/**
	 * Check whether the cell is a gas supplier
	 * @return True if the cell is a gas supplier
	 */
	public boolean is_gas()
	{
		return piece == Piece.GAS || piece == Piece.GAS_ANGLED || piece == Piece.GAS_T;
	}
	
	/**
	 * Check whether the cell is an empty cell
	 * @return True if the cell is an empty cell
	 */
	public boolean is_empty() { return piece == Piece.EMPTY; }
}
