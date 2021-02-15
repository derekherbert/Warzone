package warzone.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import warzone.model.*;
import warzone.view.GenericView;

public class MapController {

	private GameContext d_gameContext;

	public MapController(GameContext p_gameContext) {
		d_gameContext = p_gameContext;
	}
	
	/**
	 * Performs the action for the user command: showmap
	 * 
	 * Displays the map as text, showing all continents and countries and their respective neighbors.
	 */
	public GameContext showMap () {
		
		// TODO Auto-generated method stub
		
		return null;
	}
	
	/**
	 * Performs the action for the user command: savemap filename
	 * 
	 * Save a map to a text file exactly as edited (using the "domination" game map format).
	 */
	public boolean saveMap (String fileName) {
		
		// TODO Auto-generated method stub
		
		return false;
	}
	
	/**
	 * Performs the action for the user command: editmap filename
	 * 
	 * Load a map from an existing "domination" map file, or create a new map from scratch if the file does not exist
	 */
	public boolean editMap (String p_fileName) {
		
		String mapDirectory = "./doc/map/europe/";
		
		//Clear gameContext
		d_gameContext.getContinents().clear();
		d_gameContext.getCountries().clear();
		//d_gameContext.getPlayers().clear();
		
		try {
			
			File mapFile = new File(mapDirectory + p_fileName);
			Scanner scanner = new Scanner(mapFile);
			String line;
			String[] splitArray;
			int continentCtr = 1;
			int id;
			Country country;
			
			boolean processingFiles = false;
			boolean processingContinents = false;
			boolean processingCountries = false;
			boolean processingBorders = false;
			
			d_gameContext.setMapFileName(p_fileName);
			
			while (scanner.hasNextLine()) {
				
				line = scanner.nextLine();
				
				if(line.equals("[files]")) {
					
					processingFiles = true;
					processingContinents = false;
					processingCountries = false;
					processingBorders = false;
					
					line = scanner.nextLine();
				}
				else if(line.equals("[continents]")) {
					
					processingFiles = false;
					processingContinents = true;
					processingCountries = false;
					processingBorders = false;
					
					line = scanner.nextLine();
				}
				else if (line.equals("[countries]")) {
					
					processingFiles = false;
					processingContinents = false;
					processingCountries = true;
					processingBorders = false;
					
					line = scanner.nextLine();
				}
				else if (line.equals("[borders]")) {
					
					processingFiles = false;
					processingContinents = false;
					processingCountries = false;
					processingBorders = true;
					
					line = scanner.nextLine();
				}
				
				if(processingFiles) {
					
					/*
					 *  [files]
					 *	pic europe_pic.jpg
					 *	map europe_map.gif
					 *	crd europe.cards
					 */
					
					if(line.startsWith("pic")) {
						
						d_gameContext.setMapFilePic(line.substring(4));
					}
					else if(line.startsWith("map")) {
						
						d_gameContext.setMapFileMap(line.substring(4));
					}
					else if(line.startsWith("crd")) {
						
						d_gameContext.setMapFileCards(line.substring(4));
					}
				}
				else if(processingContinents && !line.trim().isEmpty()) {
					
					/*
					 *  [continents]
					 *	North_Europe 5 red
					 *	East_Europe 4 magenta
					 *	South_Europe 5 green
					 *	West_Europe 3 blue
					 */
					
					splitArray = line.split("\\s+");
										
					d_gameContext.getContinents().put(continentCtr, 
							new Continent(continentCtr, splitArray[0], Integer.parseInt(splitArray[1]), Color.valueOf(splitArray[2].toUpperCase())));
					
					continentCtr++;
				}
				else if(processingCountries && !line.trim().isEmpty()) {
					
					/*
					 *  [countries]
					 *	1 England 1 164 126
					 *	2 Scotland 1 158 44
					 *	3 N_Ireland 1 125 70
					 *	4 Rep_Ireland 1 106 90
					 */
					
					splitArray = line.split("\\s+");
					
					id = Integer.parseInt(splitArray[0]);
					country = new Country(id, splitArray[1], Integer.parseInt(splitArray[3]), Integer.parseInt(splitArray[4]));
					
					d_gameContext.getCountries().put(id, country);
					
					d_gameContext.getContinents().get(Integer.parseInt(splitArray[2])).getCountries().put(id, country);
					
					//TODO ADD CONTINENT
				}
				else if(processingBorders && !line.trim().isEmpty()) {
					
					/*
					 *  [borders]
					 *	1 8 21 6 7 5 2 3 4
					 *	2 8 1 3
					 *	3 1 2
					 *	4 22 1 5	
					 */
					
					splitArray = line.split("\\s+");
					country = d_gameContext.getCountries().get(Integer.parseInt(splitArray[0]));
					
					for(int i = 1; i < splitArray.length; i++) {
						
						id = Integer.parseInt(splitArray[i]);
						country.getNeighbors().put(id, d_gameContext.getCountries().get(id));
					}
				}
			}
		    
			scanner.close();
		    
		} catch (FileNotFoundException e) {
		      
			GenericView.println("Creating a new map: " + p_fileName);
		}
		
		return true;
	}
	
	/**
	 * Performs the action for the user command: validatemap
	 * 
	 * Verification of map correctness. The map should be automatically validated upon loading 
	 * and before saving (at least 3 types of incorrect maps). The validatemap command can be 
	 * triggered any time during map editing. 
	 */
	public boolean validateMap () {
		
		// TODO Auto-generated method stub
		
		return false;
	}

}
