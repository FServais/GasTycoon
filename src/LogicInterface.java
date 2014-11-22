/**
 * This interface contains the methods, implemented by the logic part of the game,
 * that can be used by the UI part.
 */

public interface LogicInterface {
	/**
	 * Get the current state of the game.
	 * 
	 * @return 2D array of cells representing the board.
	 */
	public CellInterface[][] getBoard();
	
	/**
	 * Get the current number of moves.
	 * 
	 * @return Number of moves.
	 */
	public int getMoves();
	
	/**
	 * Get the score of the user.
	 * 
	 * @return Score.
	 */
	public int getScore();
	
	/**
	 * Get duration of the game.
	 * 
	 * @return Duration of the game.
	 */
	public Duration getChrono();
	
	/**
	 * Check if the user won the part.
	 * 
	 * @return True if the user won, false otherwise.
	 */
	public boolean win();
	
	/**
	 * Rotate the cell located at the position (i,j).
	 * @param i Row.
	 * @param j Column.
	 */
	public void rotate(int i, int j);
	
	/**
	 * Undo the last action.
	 */
	public void undo();
	
	/**
	 * Restart the game.
	 */
	public void reset();
}
