import java.time.Duration;

/**
 * This interface contains the methods, implemented by the logic part of the game, that allows
 * any counterpart object to access to the state of the 
 */

public interface LogicInterface 
{	
	/**
	 * Get the current state of the game board
	 * @return 2D array of cells representing the board
	 */
	public CellInterface[][] getBoard();
	
	/**
	 * Get the current number of moves
	 * @return The umber of moves
	 */
	public int getMoves();
	
	/**
	 * Get the score of the user
	 * @return The score
	 */
	public int getScore();
	
	/**
	 * Get the duration of the game
	 * @return The duration of the game
	 */
	public Duration getChrono();
	
	/**
	 * Check if the user has won the game
	 * @return True if the user has won, false otherwise
	 */
	public boolean win();
	
	/**
	 * Rotate the cell located at the position (i,j)
	 * @param i Row index
	 * @param j Column index 
	 */
	public void rotate(int i, int j);
	
	/**
	 * Undo the last action
	 */
	public void undo();
	
	/**
	 * Restart the game (with the initial configuration)
	 */
	public void reset();
}
