package oose.logic.command;

/**
 * An interface implemented by any executable and reversible command
 * @author Servais Fabrice, Magera Floriane & Mormont Romain
 */
public interface Command 
{
	/**
	 * Execute the command
	 */
	public void execute();
	
	/**
	 * Perform the reverse operation executed in the Command.execute() method
	 */
	public void revert();
}
