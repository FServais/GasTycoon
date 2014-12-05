package oose.logic;

import java.util.Date;
import java.util.Stack;

import oose.interfaces.*;
import oose.logic.cells.SupplyObserver;
import oose.logic.command.Command;
import oose.logic.command.Rotation;

public class Logic implements LogicInterface, Observable , SupplyObserver
{
	private Observer observer = null;

	private int nb_moves = 0; // number of moves of the player
	private boolean[] supplied = null; // array of boolean specifying if the fireplace cells are supplied
	private Board board = null; // the game board
	private long start = -1; // game start
	private Parser parser = null; // initial config file parser
	private Stack<Command> command_stack = null; // store the previous commands of the user
	
	/**
	 * Construct a logic object
	 * @param filepath The path of the file containing the configuration
	 */
	public Logic(String filepath)
	{
		parser = new Parser(filepath);
		board = parser.get_board();
		command_stack = new Stack<Command>();
	}
	
	@Override
	public void attach(Observer observer) 
	{	
		this.observer = observer;
		// the game starts : starts the chrono
		start = System.currentTimeMillis();
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
		long now = System.currentTimeMillis();
		return (int) ((now - start) / 1000) % 60;
	}

	@Override
	public int getChronoMinutes() 
	{
		long now = System.currentTimeMillis();
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
		board = parse.get_board();
		nb_moves = 0;
		// notify the observer
		notify_obs(true);
	}

	@Override
	public void update_supply(boolean supplied, int cell_id) 
	{
		this.supplied[cell_id] = supplied;
	}

}
