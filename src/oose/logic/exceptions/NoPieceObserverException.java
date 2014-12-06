package oose.logic.exceptions;

/**
 * Thrown when an operation is triggered on an observed fireplace and no observer were 
 * initialized
 * @author Servais Fabrice, Magera Floriane & Mormont Romain
 */
public class NoPieceObserverException extends Exception 
{
	private static final long serialVersionUID = 1L;
	
	public NoPieceObserverException() { super(); }
	public NoPieceObserverException(String s) { super(s); }
}
