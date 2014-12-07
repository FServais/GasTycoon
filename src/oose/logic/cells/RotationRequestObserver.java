package oose.logic.cells;

import oose.logic.Coord;

/**
 * An interface implemented by observers of cells/pieces
 * @author Servais Fabrice, Magera Floriane & Mormont Romain
 */
public interface RotationRequestObserver 
{
	/**
	 * Action performed by the observer when a rotation request is notified
	 * @param c The coordinates which were requested to rotate
	 * @param clockwise True if the request is a clockwise rotation, false for counter-clockwise
	 */
	public void get_notified(Coord c, boolean clockwise);
}
