package oose.GUI;

import javax.swing.JButton;

public class ResetButton extends JButton {

	public ResetButton(GUI gui) {
		super("Reset");
		addMouseListener(new ResetListener(gui));
	}

}
