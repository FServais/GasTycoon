package oose.boardRepresentation;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import oose.interfaces.CellInterface;
import oose.interfaces.Orientation;
import oose.interfaces.Piece;

public class BoxImageLibrary {
	private BoxImage[] boxImages;
	private static BoxImageLibrary instance = null;
	
	private BoxImageLibrary(){
		String cellImagePath[] = new String[] {"FIREPLACE_DOWN.png", "FIREPLACE_LEFT.png", "FIREPLACE_RIGHT.png", "FIREPLACE_UP.png", "FIREPLACE_DOWN_ON.png", "FIREPLACE_LEFT_ON.png", "FIREPLACE_RIGHT_ON.png", "FIREPLACE_UP_ON.png", 
				"PIPELINE_ANGLED_DOWN.png", "PIPELINE_ANGLED_LEFT.png", "PIPELINE_ANGLED_RIGHT.png", "PIPELINE_ANGLED_UP.png", "PIPELINE_ANGLED_DOWN_ON.png", "PIPELINE_ANGLED_LEFT_ON.png", "PIPELINE_ANGLED_RIGHT_ON.png", "PIPELINE_ANGLED_UP_ON.png",
				"T_DOWN.png", "T_LEFT.png", "T_RIGHT.png", "T_UP.png", "T_DOWN_ON.png", "T_LEFT_ON.png", "T_RIGHT_ON.png", "T_UP_ON.png",
				"GAS_DOWN.png", "GAS_LEFT.png", "GAS_RIGHT.png", "GAS_UP.png", 
				"GAS_T_DOWN.png", "GAS_T_LEFT.png", "GAS_T_RIGHT.png", "GAS_T_UP.png", 
				"GAS_ANGLED_DOWN.png", "GAS_ANGLED_LEFT.png", "GAS_ANGLED_RIGHT.png", "GAS_ANGLED_UP.png",
				"PIPELINE_RIGHT.png", "PIPELINE_UP.png", "PIPELINE_RIGHT_ON.png", "PIPELINE_UP_ON.png",
				"EMPTY.png"};
		try {
			boxImages = new BoxImage[41];
			
			for(int i=0;i<41;++i){
				boxImages[i] = new BoxImage(ImageIO.read(new File("boxes/"+cellImagePath[i])));
			}
		} catch (IOException e) {
			System.err.println("Error when reading cells' picture");
		}
	}
	
	public static BoxImageLibrary getUniqueInstance() {
		if (instance == null)
			instance = new BoxImageLibrary();
		
		return instance;
	}
	
	public BoxImage selectBoxImage(CellInterface c){
		Piece typeP = c.getPieceType();
		Orientation orientation = c.getOrientation();
		
		int orientOffset = 0;
		switch(orientation){
			case DOWN:
				orientOffset = 0;
				break;
			case LEFT:
				orientOffset = 1;
				break;
			case RIGHT:
				orientOffset = 2;
				break;
			case UP:
				orientOffset = 3;
				break;
		}
		int indx = 0;
		switch(typeP){
			case FIREPLACE:
				indx = orientOffset;
				if(c.isSupplied())
					indx += 4;
				return boxImages[indx];
			case PIPELINE_ANGLED:
				indx = orientOffset+8;
				if(c.isSupplied())
					indx += 4;
				return boxImages[indx];
			case PIPELINE_T:
				indx = orientOffset+16;
				if(c.isSupplied())
					indx += 4;
				return boxImages[indx];
			case GAS:
				indx = orientOffset+24;
				return boxImages[indx];
			case GAS_T:
				indx = orientOffset+28;
				return boxImages[indx];
			case GAS_ANGLED:
				indx = orientOffset+32;
				return boxImages[indx];
			case PIPELINE:
				indx = 36;
				if(c.isSupplied())
					indx += 2;
				if(orientation.equals(Orientation.RIGHT) || orientation.equals(Orientation.LEFT))
					return boxImages[indx];
				else if((orientation.equals(Orientation.UP) || orientation.equals(Orientation.DOWN)))
					return boxImages[indx+1];
			case EMPTY:
				return boxImages[40];
				
		}
		return null;
	}
}
