package oose.GUI;

import javax.swing.JButton;

public class UndoButton extends JButton {

	public UndoButton(GUI gui) {
		super("Undo");
		addMouseListener(new UndoListener(gui));
	}

}
