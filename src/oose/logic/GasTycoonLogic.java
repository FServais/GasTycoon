package oose.logic;

import oose.interfaces.CellInterface;
import oose.interfaces.LogicInterface;
import oose.interfaces.Observable;
import oose.interfaces.Observer;

public class GasTycoonLogic implements LogicInterface, Observable 
{

	Observer observer = null;
	
	@Override
	public void attach(Observer observer) 
	{	
		this.observer = observer;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMoves() 
	{
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getChronoMinutes() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean win() 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void rotate(int i, int j) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void undo() 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void reset() 
	{
		// TODO Auto-generated method stub

	}

}
