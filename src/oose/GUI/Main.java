package oose.GUI;
import java.io.FileNotFoundException;

import oose.logic.*;
import oose.logic.exceptions.BadFileConfigurationException;

public class Main {

	public static void main(String[] args) {
		Logic logic = null;
		try {
			logic = new Logic(args[0]);
		} catch (FileNotFoundException | BadFileConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GUI gui = new GUI(logic);
		logic.attach(gui);
	}

}