package oose.logic;

import java.io.FileNotFoundException;
import java.util.Stack;

import oose.interfaces.*;
import oose.logic.cells.PieceObserver;
import oose.logic.command.*;
import oose.logic.exceptions.BadFileConfigurationException;
import oose.logic.exceptions.BadPeriodException;

/**
 * A class for handling the Gastycoon game logic
 * @author Servais Fabrice, Magera Floriane & Mormont Romain
 */
public class Logic implements LogicInterface, Observable, PieceObserver
{
	private Observer observer = null;

	private int nb_moves = 0; // number of moves of the player
	private boolean[] supplied = null; // array of boolean specifying if the fireplace cells are supplied
	private Board board = null; // the game board
	private Parser parser = null; // initial config file parser
	private Stack<Command> command_stack = null; // store the previous commands of the user
	private PeriodicNotifier pn; // the periodic notifier for notifying chronometer update
	
	/**
	 * Construct a logic object
	 * @param filepath The path of the file containing the configuration
	 * @throws BadFileConfigurationException If the configuration file is not well formated
	 * @throws FileNotFoundException If the configuration file is not found
	 */
	public Logic(String filepath) throws FileNotFoundException, BadFileConfigurationException
	{
		parser = new Parser(filepath);
		board = parser.get_board();
		command_stack = new Stack<Command>();
		pn = get_notifier();
	}
	
	/**
	 * Return a periodic notifier object
	 * @return The periodic notifier
	 */
	private PeriodicNotifier get_notifier()
	{
		try {
			return new PeriodicNotifier(this, 1000);
		} catch (BadPeriodException e) { return null; }
	}
	
	@Override
	public void attach(Observer observer) 
	{	
		this.observer = observer;
		// the game starts : starts the chrono
		pn.start();
	}

	@Override
	public void notify_obs(boolean board_updated) 
	{
		if(observer == null)
			return;
		observer.update(board_updated);
	}

	@Override
	public CellInterface[][] getBoard() 
	{
		return board.get_array();
	}

	@Override
	public int getMoves() 
	{
		return nb_moves;
	}

	@Override
	public int getScore() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getChronoSeconds() 
	{
		long now = System.currentTimeMillis(), 
			 start = pn.get_start_time();
		return (int) ((now - start) / 1000) % 60;
	}

	@Override
	public int getChronoMinutes() 
	{
		long now = System.currentTimeMillis(), 
			 start = pn.get_start_time();
		return (int) ((now - start) / 60000) % 60;
	}

	@Override
	public boolean win() 
	{
		boolean win = true;
		
		for(boolean fp_supplied : supplied)
			win &= fp_supplied;
		
		return win;
	}

	@Override
	public void rotate(int i, int j) 
	{
		Rotation rot = new Rotation(i,j,board);
		rot.execute();
		command_stack.push(rot);
		nb_moves += 1;
		
		synchronized(this) { notify_obs(true); }
	}

	@Override
	public void undo() 
	{
		Command rot = command_stack.pop();
		rot.revert();
	}

	@Override
	public void reset() 
	{
		command_stack.clear();
		board = parser.get_board();
		nb_moves = 0;
		
		pn.stop_notifier();
		
		// notify the observer
		synchronized(this) { notify_obs(true); }
		
		pn = get_notifier();
		pn.start(); // restart chronometer
	}

	@Override
	public void update_supply(boolean supplied, int cell_id) 
	{
		/**
		 * We can ignore the update from the cells (if it does not come from a fireplace)
		 * because the update of supply can only be caused by a rotation so the cells will
		 * eventually trigger a update_rotate call at some point
		 */
		if(cell_id == -1) 
			return;
		
		this.supplied[cell_id] = supplied;
	}

	@Override
	public void update_rotate() 
	{
		observer.update(true);
	}
}
