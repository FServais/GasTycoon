package oose.GUI;

import oose.interfaces.CellInterface;
import oose.interfaces.LogicInterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	private BoxBoard board;
	private ChronoPanel chrono;
	private ClicksCounterPanel clicksCounter;
	private UndoButton undo;
	private ResetButton reset;
	
	public MainFrame(GUI gui) {
		
		super("GasTycoon");
		setLayout(new GridBagLayout());
		
		board = new BoxBoard(gui);
		chrono = new ChronoPanel();
		clicksCounter = new  ClicksCounterPanel();
		undo = new UndoButton(gui);
		reset = new ResetButton(gui);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.5;
		c.insets = (new Insets(10,10,10,10));
		
		add(chrono, c);
		c.gridx++;
		add(clicksCounter,c);
		c.gridy++;
		add(undo,c);
		c.gridx--;
		add(reset,c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 2;
		c.gridy = 0;
		
		c.weighty = 0.5;
		add(board, c);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		pack();
	}

	private static final long serialVersionUID = 1L;

	public void updateChrono(int chronoMinutes, int chronoSeconds) {
		chrono.update(chronoMinutes, chronoSeconds);
	}

	public void updateClicksCounter(int moves) {
		clicksCounter.update(moves);
	}

	public void updateBoard(CellInterface[][] board) {
		this.board.update(board);
	}

}
