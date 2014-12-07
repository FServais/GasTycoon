package oose.GUI;

import javax.swing.JLabel;

public class ClicksCounterPanel extends JLabel {
	
	private int clicksNumber;
	
	public ClicksCounterPanel() {
		clicksNumber = 0; 
		display();
	}
	
	public void update(int n) {
		clicksNumber = n;
		display();
	}
	
	private void display() {
		setText("CLICKS: " + Integer.toString(clicksNumber));
	}

}
