package oose.GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UndoListener implements MouseListener {

	private GUI gui;
	
	public UndoListener(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		gui.performUndo();
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
