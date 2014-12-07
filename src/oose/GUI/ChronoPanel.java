package oose.GUI;

import javax.swing.JLabel;

public class ChronoPanel extends JLabel {
	
	private String minutes;
	private String seconds;
	
	public ChronoPanel() {
		minutes = "00";
		seconds = "00";
		display();
	}
	
	public void update(int min, int sec) {
		String digit1 = new String("");
		
		if(min<10)
			digit1 = "0";
		
		minutes = digit1 + Integer.toString(min);
		
		digit1 = "";
		
		if(sec<10)
			digit1 = "0";
		
		seconds = digit1 + Integer.toString(sec);
		
		display();
	}
	
	private void display() {
		setText("CHRONO: " + minutes + ":" + seconds);
	}

}
