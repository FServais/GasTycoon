package oose.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WinDialog extends JDialog implements ActionListener {
	
	private String text = "Congratulations, you won! Your score: ";
	private JLabel message;
  
	public WinDialog(JFrame parent) {
	    super(parent, "", false);
	    if (parent != null) {
	      Dimension parentSize = parent.getSize(); 
	      Point p = parent.getLocation(); 
	      setLocation(p.x + parentSize.width / 4, p.y + parentSize.height / 4);
	    }
	    JPanel messagePane = new JPanel();
	    message = new JLabel(text);
	    messagePane.add(message);
	    getContentPane().add(messagePane);
	    JPanel buttonPane = new JPanel();
	    JButton button = new JButton("OK"); 
	    buttonPane.add(button); 
	    button.addActionListener(this);
	    getContentPane().add(buttonPane, BorderLayout.SOUTH);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    pack(); 
	}
  
	public void actionPerformed(ActionEvent e) {
	    setVisible(false); 
	    dispose(); 
	}
  
	public void setScore(int score) {
		message.setText(text + Integer.toBinaryString(score));
	}

}
