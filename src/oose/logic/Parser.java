package oose.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import oose.interfaces.*;
import oose.logic.cells.*;
import oose.logic.exceptions.*;

/**
 * A class for parsing a configuration file and getting a game board
 * @author Servais Fabrice, Magera Floriane & Mormont Romain
 */
public class Parser 
{
	private CellBuilder builder = null;
	
	/** content parsed from the file */
	int width, height;
	public Orientation[][] orientations;
	public Piece[][] pieces;
	
	/**
	 * Construct a parser object
	 * @param filepath The configuration file path
	 * @throws FileNotFoundException Thrown if the file is not found
	 * @throws BadFileConfigurationException Thrown if the file format is invalid
	 */
	public Parser(String filepath) throws FileNotFoundException, BadFileConfigurationException 
	{
		builder = CellBuilder.get_instance();
		parse(filepath);
	}

	/**
	 * Return a board built with the file configuration
	 * @return The brand new board
	 */
	public Board get_board() 
	{
		Cell[][] cell_array = new Cell[height][width];
		
		for(int i = 0; i < height; ++i)
			for(int j = 0; j < width; ++j)
				cell_array[i][j] = builder.build_cell(orientations[i][j], pieces[i][j], i, j);
		
		return new Board(cell_array);
	}

	/**
	 * Parses the configuration file
	 * @param filepath Path of the configuration file
	 * @throws FileNotFoundException Thrown if the config. file is not found
	 * @throws BadFileConfigurationException Thrown if the config. is not correctly formatted
	 */
	private void parse(String filepath) 
			throws FileNotFoundException, BadFileConfigurationException
	{
		FileReader fr = new FileReader(filepath);
		BufferedReader br = new BufferedReader(fr);
		
		try
		{
			// read board dimensions
			String[] wh = br.readLine().split("\\s");
			
			height = Integer.parseInt(wh[0]);
			width = Integer.parseInt(wh[1]);
			
			init_arrays();
			
			// read configuration
			int line_count = 0;		
			for(int i = 0; i < height; ++i, ++line_count)
			{
				String line = br.readLine();
				String[] splitted_line = line.split("\\s");
				
				if(width != splitted_line.length)
				{
					br.close();
					fr.close();
					throw new BadFileConfigurationException("Invalid number of column at row " + (i + 1));
				}
				for(int j = 0; j < width; ++j)
				{
					String piece_s = splitted_line[i].substring(0,1), 
						   orient_s = splitted_line[i].substring(1,2);
					
					pieces[i][j] = builder.get_piece(Integer.parseInt(piece_s));
					orientations[i][j] = builder.get_orient(Integer.parseInt(orient_s));
				}
			}
			
			if(line_count != height) // check if the number of row is ok
			{
				br.close();
				fr.close();
				throw new BadFileConfigurationException("Invalid number of rows");
			}
		}
		catch(IOException e)
		{ } 
		catch (NumberFormatException | BadIdException e) 
		{
			try
			{
				br.close(); 
				fr.close();
			}
			catch(IOException e1) {}
			
			throw new BadFileConfigurationException(e.getMessage());
		}
		finally
		{
			try
			{
				br.close();
				fr.close();
			} catch(IOException e) {}
		}
	}
	
	/**
	 * Initialize the orientations and pieces array
	 */
	private void init_arrays()
	{
		pieces = new Piece[height][width];
		orientations = new Orientation[height][width];
	}

}
