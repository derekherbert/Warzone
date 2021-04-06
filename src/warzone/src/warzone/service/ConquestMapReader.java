package warzone.service;

import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Scanner;

import warzone.model.Continent;
import warzone.model.Country;
import warzone.model.GameContext;
import warzone.model.LoadMapPhase;
import warzone.model.LogEntryBuffer;

public class ConquestMapReader {
	/**
	 * game context
	 */
	private GameContext d_gameContext;

	/**
	 * log entry buffer
	 */
	private LogEntryBuffer d_logEntryBuffer;
	
	/**
	 * Map Service
	 */
	private MapService d_mapService;
	
	/**
	 * The constructor of this class
	 * @param p_gameContext the current game context
	 */
	public ConquestMapReader(GameContext p_gameContext) {
		d_gameContext = p_gameContext;
		d_logEntryBuffer = d_gameContext.getLogEntryBuffer();
		d_mapService = new MapService(d_gameContext);
	}
	
	/**
	 * Game starts by user selection of a user-saved map file, which loads the map as a connected directed graph.
	 * This method will load map using 'conquest' game format
	 * 
	 * @param p_fileName file name of map
	 * @return if map successfully loaded
	 */
	public boolean loadConquestMap(String p_fileName) {
		
		String l_mapDirectory = null;
		
		try {
			//Get the map directory from the properties file
			Properties l_properties = new Properties();
			l_properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
			l_mapDirectory = l_properties.getProperty("gameMapDirectory");
			
		} catch (IOException ex) {
			d_logEntryBuffer.logAction("ERROR", "Error loading properties file.");
			return false;
		}
		
		try {
			
			//Clear gameContext
			d_gameContext.reset();

			File l_mapFile = new File(l_mapDirectory + p_fileName);
			
			d_gameContext.setMapFileName(p_fileName);

			//Specified file name does not exist (new map)
			if(!l_mapFile.exists() || l_mapFile.isDirectory()) {
				d_logEntryBuffer.logAction("ERROR", "The following map file is invalid, please select another one: " + p_fileName);
				return false;
			}
			
			Scanner l_scanner = new Scanner(l_mapFile);
			String l_line;
			String[] l_splitArray;
			int l_continentCtr = 1;
			int l_id = 1;
			Country l_country;

			LoadMapPhase l_loadMapPhase = null;
			
			while (l_scanner.hasNextLine()) {
				l_line = l_scanner.nextLine();

				// determine which part it is
				// file part
				if(l_line.equals("[Continents]")) {

					l_loadMapPhase = LoadMapPhase.CONTINENTS;
					l_line = l_scanner.nextLine();
				}
				// continents part
				else if(l_line.equals("Territories")) {
					l_loadMapPhase = LoadMapPhase.TERRITORIES;
					l_line = l_scanner.nextLine();
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
						Cockpit=5
						Right Thruster=6
						Left Thruster=6
						Right Cargo=10
					 */
					
					l_splitArray = l_line.split("=");
										
					d_gameContext.getContinents().put(l_continentCtr,
							new Continent(l_continentCtr, l_splitArray[0], Integer.parseInt(l_splitArray[1]), null));
					
					l_continentCtr++;
				}
				//read territories part
				else if(l_loadMapPhase == LoadMapPhase.TERRITORIES && !l_line.trim().isEmpty()) {
					
					/*
					 *  [territories]
						Cockpit01,658,355,Cockpit,Cockpit02,Territory33
						Cockpit02,658,375,Cockpit,Cockpit01,Cockpit03,Territory33,Territory85
						Cockpit03,658,395,Cockpit,Cockpit02,Territory85
					 */
					
					l_splitArray = l_line.split(",");
					Continent l_continent = null;
					for (Entry<Integer, Continent> l_entry : d_gameContext.getContinents().entrySet()) {
						if (l_entry.getValue().getContinentName().equals(l_splitArray[3])) {
							l_continent = l_entry.getValue();
						}
					}
					
					// no such continent
					if (l_continent == null) {
						d_logEntryBuffer.logAction("ERROR", "No such continent: " + l_splitArray[3]);
						return false;
					}
					
					l_country = new Country(l_id, l_splitArray[0], Integer.parseInt(l_splitArray[1]),
							Integer.parseInt(l_splitArray[2]), l_continent);
					
					d_gameContext.getCountries().put(l_id, l_country);
					d_gameContext.getContinents().get(Integer.parseInt(l_splitArray[2])).getCountries().put(l_id, l_country);
				}
			}
			//close reading the file
			l_scanner.close();
			
			d_logEntryBuffer.logAction("SUCCESS", "Map succesfully loaded: " + p_fileName);
				
		} catch (Exception e) {
			d_logEntryBuffer.logAction("ERROR", "An error occured reading the map file: " + p_fileName);
			return false;
		}
		return false;
	}
	
	/**
	 * Save a map to a text file exactly as edited (using the "conquest" game map format).
	 * @param p_fileName the filename
	 * @return true if successfully save the map, otherwise return false
	 * @throws IOException the exception of saving files
	 */
	public boolean saveConquestMap(String p_fileName) throws IOException {

		return false;
	}
}
