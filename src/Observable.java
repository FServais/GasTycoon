/**
 * Class extended by any class of which the instance deal with the state of the GasTycoon game (to be observed) 
 */
public abstract class Observable 
{	
	private Observer observer = null; // The state observer
	
	/**
	 * Link a observer to object
	 * @param observer The observer to attach
	 * @note Any previously attached observer is discarded 
	 */
	public void attach(Observer observer)
	{
		this.observer = observer;
	}

	/**
	 * Notifies the observer if the state of the GasTycoon game was updated
	 * @param board_updated True if the board was updated (and thus need to be checked by the observer), false otherwise
	 */
	public void notify_obs(boolean board_updated)
	{
		if(observer != null)
			observer.update(board_updated);
	}
}
