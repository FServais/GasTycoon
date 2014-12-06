package oose.logic.exceptions;

/**
 * Thrown when an id given instead of of an enumeration element and that this id is not valid
 * @author Servais Fabrice, Magera Floriane & Mormont Romain
 */
public class BadIdException extends Exception 
{
	private static final long serialVersionUID = 1L;
	
	public BadIdException() { super(); }
	public BadIdException(String s) { super(s); }
}
