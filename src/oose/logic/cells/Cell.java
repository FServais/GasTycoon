package oose.logic.cells;

import java.util.Vector;

import oose.interfaces.CellInterface;
import oose.interfaces.Orientation;
import oose.interfaces.Piece;

/**
 * Class representing a cell of the board
 * @author Servais Fabrice, Magera Floriane & Mormont Romain
 */
public abstract class Cell implements CellInterface 
{
	Orientation orientation; /** cell orientation */
	Piece piece; /** cell piece */
	Cell[] neighbors = null; /** array of neighbors : top right bottom left*/
	boolean supplied = false;  /** true if the cell is supplied */
	boolean[] connections = null; /** array specifying if the current cell can connect to its neighbours (top right bottom left)  */
	
	/**
	 * Initialize cell members
	 */
	private Cell()
	{
		neighbors = new Cell[4];
		supplied = false;
		connections = new boolean[4];
	}
	
	/**
	 * Builds a cell 
	 * @param o The cell orientation
	 * @param p The cell piece
	 */
	public Cell(Orientation o, Piece p)
	{
		this();
		orientation = o;
		piece = p;
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
	
	public void set_supplied(boolean b)
	{
		supplied = b;
	}
	
	/**
	 * Change the state to "supply" and propagate to the neighbors.
	 */
	public void update_supplied(){
		if(supplied)
			return;
		
		supplied = true;
		
		for(int i = 0 ; i < 4 ; ++i)
			if(connections[i] && neighbors[i] != null && neighbors[i].has_link_with((i+2)%4)) //(i+2)%4 : top (0) <-> bottom (2) ; left (3) <-> right (1)
				neighbors[i].update_supplied();
		
	}
	
	/**
	 * Check if the cell have a link with another cell situated to the <orientation>, 
	 * where 'orientation' = {0 : top, 1 : right, 2 : bottom, 3 : left}.
	 * @param orientation Location of the Cell to compare with ({0 : top, 1 : right, 2 : bottom, 3 : left})
	 * @return True if there is a link, false otherwise.
	 */
	public boolean has_link_with(int orientation){
		switch(orientation)
		{
		case 0:
			return has_top_link();
		case 1:
			return has_right_link();
		case 2:
			return has_bottom_link();
		case 3:
			return has_left_link();
		default:
			return false;
		}
	}
	
	@Override
	public void clockwiseNext() 
	{
		rotate(true);
	}

	@Override
	public void counterClockwiseNext() 
	{
		rotate(false);
	}
	
	/**
	 * Rotate the cell int the given direction
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
		case RIGHT:
			return clockwise ? Orientation.DOWN : Orientation.UP;
		default: 
			return Orientation.UP; // normally, cannot be reached but otherwise error
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
	 * Set the current cell neighbors
	 * @param neighbors The neighbors. They must be stored in the following order [top, right, bottom, left]
	 */
	public void set_neighbors(Cell[] neighbors)
	{
		this.neighbors = neighbors;
	}

	/**
	 * Check if there is a link to the top Cell.
	 * @return True if there is a link, false otherwise.
	 */
	protected abstract boolean has_top_link();
	
	/**
	 * Check if there is a link to the right Cell.
	 * @return True if there is a link, false otherwise.
	 */
	protected abstract boolean has_right_link();
	
	/**
	 * Check if there is a link to the bottom Cell.
	 * @return True if there is a link, false otherwise.
	 */
	protected abstract boolean has_bottom_link();
	
	/**
	 * Check if there is a link to the left Cell.
	 * @return True if there is a link, false otherwise.
	 */
	protected abstract boolean has_left_link();
	
}
