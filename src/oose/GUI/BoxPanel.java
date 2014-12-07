package oose.GUI;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import oose.boardRepresentation.BoxImage;
import oose.boardRepresentation.BoxImageLibrary;
import oose.interfaces.CellInterface;

class BoxPanel extends JPanel {
    
    private int row, column;
	private BoxImage image;
    
    public BoxPanel(int i, int j, BoardMouseListener m)
    {
         row = i;
         column = j;
         setPreferredSize(new Dimension(64, 64));
         this.addMouseListener(m);
    }
    
    public void paintComponent(Graphics g) 
    {
        if(image != null) 
        	g.drawImage(image.getImage(),0,0,getWidth(), getHeight(),this);
    }
    
    public void setBoxImage(CellInterface cell) {
    	BoxImageLibrary librairy = BoxImageLibrary.getUniqueInstance();
    	image = librairy.selectBoxImage(cell);
    }
    
    public int getRow() {
    	return row;
    }
    
    public int getColumn() {
    	return column;
    }

}