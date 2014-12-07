package oose.GUI;

import oose.interfaces.LogicInterface;
import oose.interfaces.Observer;

public class GUI implements Observer {
	private LogicInterface game;
	private MainFrame window;
	private WinDialog winDialog;
	
	public GUI(LogicInterface game) {
		this.game = game;
		window = new MainFrame(this);
		winDialog = new WinDialog(window);
	}
	
	public LogicInterface getGame() {
		return game;
	}
	
	public void linkLogic(LogicInterface game) {
		this.game = game;
	}
	
	public void update(boolean board_need_update) {
		window.updateChrono(game.getChronoMinutes(), game.getChronoSeconds());
		window.updateClicksCounter(game.getMoves());
		
		if (board_need_update) {
			window.updateBoard(game.getBoard());
		}
		
		if(game.win()) {
			winDialog.setScore(game.getScore());
			winDialog.setVisible(true);
		}
	}

	public void performUndo() {
		game.undo();	
	}

	public void performReset() {
		game.reset();	
	}

	public void performRotation(int row, int column) {
		game.rotate(row, column);
	}
}
