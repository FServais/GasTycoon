package oose.interfaces;

/**
 * @interface Observer
 * This interface must be implemented by every observer of the GasTycoon oose.logic part
 */
public interface Observer 
{
	/**
	 * Updates the observer state 
	 * @param board_need_update True if the GasTycoon board was updated by the observed object
	 */
	public void update(boolean board_need_update);
}
