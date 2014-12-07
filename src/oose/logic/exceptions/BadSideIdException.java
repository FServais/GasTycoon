package oose.logic.exceptions;

/**
 * Exception thrown when a given cell side id is invalid (i.e. not in [0,4[)
 * @author Servais Fabrice, Magera Floriane & Mormont Romain
 */
public class BadSideIdException extends Exception 
{
	private static final long serialVersionUID = 1L;

	public BadSideIdException() { super(); }
	public BadSideIdException(String s) { super(s); }
}
