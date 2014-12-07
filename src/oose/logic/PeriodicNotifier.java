package oose.logic;

import oose.interfaces.Observable;
import oose.logic.exceptions.BadPeriodException;

/**
 * Thread for notifying the observer periodically
 * @author Servais Fabrice, Magera Floriane & Mormont Romain
 */
public class PeriodicNotifier extends Thread 
{
	private boolean stop = false; // true if the notifier must be stopped
	private Observable observable = null; // the observer to notify 
	private int period = 0; // notification period
	private long start_time = -1; // time when the periodic notifier was started
	
	/**
	 * Create a periodic notifier
	 * @param o The observable on 
	 * @param period
	 * @throws BadPeriodException
	 */
	public PeriodicNotifier(Observable o, int period) throws BadPeriodException
	{
		if(period <= 20)
			throw new BadPeriodException("The notification period should be bigger than 20");

		this.setDaemon(true);
		this.period = period;
		this.observable = o;
		this.stop = false;
	}
	
	public void run()
	{
		try
		{	
			stop = false;
			start_time = System.currentTimeMillis();
			while(!stop)
			{
				Thread.sleep(period);
				
				synchronized(observable)
				{
					observable.notify_obs(false);
				}
			}
		} catch (InterruptedException e) { System.err.println(e.getMessage()); }
	}
	
	/**
	 * Stops the periodic notifier
	 */
	public synchronized void stop_notifier()
	{
		stop = true;
	}
	
	/**
	 * Return the time when the periodic notifier started to execute
	 * @return The execution start time
	 */
	public synchronized long get_start_time()
	{
		return start_time;
	}
	
	/**
	 * Returns true if the periodic notifier was started mode than one hour ago
	 * @return True if started more than one hour ago, galse otherwise
	 */
	public synchronized boolean more_than_hour()
	{
		long now = System.currentTimeMillis();
		
		return ((now - start_time) / 3600000) > 0;
	}
}
