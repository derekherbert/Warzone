package warzone.state;
import warzone.service.*;

import java.io.File;
import java.util.Scanner;

import warzone.controller.MapController;
import warzone.model.*;
import warzone.view.*;

/**
 *	ConcreteState of the State pattern. In this example, defines behavior 
 *  for commands that are valid in this state, and for the others signifies  
 *  that the command is invalid. 
 */
public class MapEditor extends Phase {

	private MapService d_mapService;
	
	public MapEditor(GameEngine p_ge) {
		super(p_ge);
		d_mapService = new MapService(d_gameContext);
	}

	/**
	 *  Call this method to go the the next state in the sequence. 
	 */
	public void next() {
		d_gameEngine.setPhase(new Startup(d_gameEngine));
	}
	
	 public void addContinent(String p_parameters){
		 //todo
	 }	
	 public void removeContinent(String p_parameters) {
		 //todo
	 }	
	 public void addCountry (String p_parameters) {
		 //todo
	 }	
	 public void removeCountry(String p_parameters) {
		 //todo
	 }	
	 public void addNeighbor (String p_parameters) {
		 //todo
	 }		
	 public void removeNeighbor (String p_parameters) {
		 //todo
	 }	
	 
	/**
	 * Performs the action for the user command: showmap
	 *
	 * Displays the map as text, showing all continents and countries and their respective neighbors.
	 */
	public void showMap () {

		MapView.printMap(d_gameContext);
	}
	
	 public void saveMap (String p_fileName) {
		 //todo
	 }	
	
	/**
	 * Load a map from an existing â€œdominationâ€� map file,
	 * or create a new map from scratch if the file does not exist.
	 * 
	 * @param p_fileName file name
	 */	
	public void editMap (String p_fileName) {
		
		String l_mapDirectory = WarzoneProperties.getWarzoneProperties().getGameMapDirectory();
			
		try {
			
			//Clear gameContext
			d_gameContext.reset();

			File l_mapFile = new File(l_mapDirectory + p_fileName);
			
			d_gameContext.setMapFileName(p_fileName);

			//Specified file name does not exist (new map)
			if(!l_mapFile.exists() || l_mapFile.isDirectory()) {
				GenericView.printError("The following map file is invalid, please select another one: " + p_fileName);
				return;
			}
			
			Scanner l_scanner = new Scanner(l_mapFile);
			String l_line;
			String[] l_splitArray;
			int l_continentCtr = 1;
			int l_id;
			Country l_country;

			LoadMapPhase l_loadMapPhase = null;
			
			while (l_scanner.hasNextLine()) {
				l_line = l_scanner.nextLine();

				// determine which part it is
				// file part
				if(l_line.equals("[files]")) {

					l_loadMapPhase = LoadMapPhase.FILES;
					l_line = l_scanner.nextLine();
				}
				// continents part
				else if(l_line.equals("[continents]")) {
					
					l_loadMapPhase = LoadMapPhase.CONTINENTS;
					l_line = l_scanner.nextLine();
				}
				//countries part
				else if (l_line.equals("[countries]")) {
					
					l_loadMapPhase = LoadMapPhase.COUNTRIES;
					l_line = l_scanner.nextLine();
				}
				//borders part
				else if (l_line.equals("[borders]")) {
					
					l_loadMapPhase = LoadMapPhase.BORDERS;
					
					if(!l_scanner.hasNextLine())
						l_loadMapPhase = LoadMapPhase.COMPLETE;
					else{
						l_line = l_scanner.nextLine();
					}
				}

				// read file part
				if(l_loadMapPhase == LoadMapPhase.FILES) {
					
					/*
					 *  [files]
					 *	pic europe_pic.jpg
					 *	map europe_map.gif
					 *	crd europe.cards
					 */
					
					if(l_line.startsWith("pic")) {
						
						d_gameContext.setMapFilePic(l_line.substring(4));
					}
					else if(l_line.startsWith("map")) {
						
						d_gameContext.setMapFileMap(l_line.substring(4));
					}
					else if(l_line.startsWith("crd")) {
						
						d_gameContext.setMapFileCards(l_line.substring(4));
					}
				}
				//read continent part
				else if(l_loadMapPhase == LoadMapPhase.CONTINENTS && !l_line.trim().isEmpty()) {
					
					/*
					 *  [continents]
					 *	North_Europe 5 red
					 *	East_Europe 4 magenta
					 *	South_Europe 5 green
					 *	West_Europe 3 blue
					 */
					
					l_splitArray = l_line.split("\\s+");
										
					d_gameContext.getContinents().put(l_continentCtr,
							new Continent(l_continentCtr, l_splitArray[0], Integer.parseInt(l_splitArray[1]), l_splitArray[2]));
					
					l_continentCtr++;
				}
				//read countries part
				else if(l_loadMapPhase == LoadMapPhase.COUNTRIES && !l_line.trim().isEmpty()) {
					
					/*
					 *  [countries]
					 *	1 England 1 164 126
					 *	2 Scotland 1 158 44
					 *	3 N_Ireland 1 125 70
					 *	4 Rep_Ireland 1 106 90
					 */
					
					l_splitArray = l_line.split("\\s+");
					
					l_id = Integer.parseInt(l_splitArray[0]);
					l_country = new Country(l_id, l_splitArray[1], Integer.parseInt(l_splitArray[3]),
							Integer.parseInt(l_splitArray[4]), d_gameContext.getContinents().get(Integer.parseInt(l_splitArray[2])));
					
					d_gameContext.getCountries().put(l_id, l_country);
					
					d_gameContext.getContinents().get(Integer.parseInt(l_splitArray[2])).getCountries().put(l_id, l_country);
				}
				//read border part
				else if(l_loadMapPhase == LoadMapPhase.BORDERS && !l_line.trim().isEmpty()) {
					
					/*
					 *  [borders]
					 *	1 8 21 6 7 5 2 3 4
					 *	2 8 1 3
					 *	3 1 2
					 *	4 22 1 5	
					 */
					
					l_splitArray = l_line.split("\\s+");
					l_country = d_gameContext.getCountries().get(Integer.parseInt(l_splitArray[0]));
					
					for(int l_temp = 1; l_temp < l_splitArray.length; l_temp++) {
						
						l_id = Integer.parseInt(l_splitArray[l_temp]);
						l_country.getNeighbors().put(l_id, d_gameContext.getCountries().get(l_id));
					}
				}
			}

			//close reading the file
			l_scanner.close();
			
			GenericView.printSuccess("Map succesfully loaded: " + p_fileName);
		    
		} catch (Exception e) {
			GenericView.printError("An error occured reading the map file: " + p_fileName);
		}
	 }		
	 public void validateMap() {
		 //todo
	 }	

	 public void addPlayer(String p_playerName) {
		 printInvalidCommandMessage();
	 }	
	 public void removePlayer(String p_playerName){
		 printInvalidCommandMessage();
	 }	
	 public void loadMap(String p_fileName){
		 printInvalidCommandMessage();
	 }		
	 public void populatecountries(){
		 printInvalidCommandMessage();
	 }		
	 public void reinforcement(){
		 printInvalidCommandMessage();
	 }	
 
	public void issueOrder(){
		 printInvalidCommandMessage();
	 }	
	public void executeOrder(){
		 printInvalidCommandMessage();
	 }	
	
	

}
